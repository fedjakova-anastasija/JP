package com.company.spreadsheet;

import com.company.cell.Cell;
import com.company.cell.Formula;
import com.company.cell.Number;
import com.company.cell.Str;

import java.util.*;

public class Spreadsheet {
  private final List<List<Cell>> spreadsheet = new ArrayList<>();
  private final HashMap<Position, HashSet<Position>> link = new HashMap<>();

  public Spreadsheet(Position bottomRightAddress) {
    int rows = bottomRightAddress.getRow();
    int columns = bottomRightAddress.getColumn();

    rows = (rows < 1) ? 1 : rows;
    columns = (columns < 1) ? 1 : columns;

    for (int row = 0; row < rows; ++row) {
      spreadsheet.add(new ArrayList<>());

      for (int column = 0; column < columns; ++column) {
        Str value = new Str();
        spreadsheet.get(row).add(value);
      }
    }
  }

  List<List<Cell>> getTable() {
    return spreadsheet;
  }

  int rows() {
    return spreadsheet.size();
  }

  int columns() {
    return spreadsheet.get(0).size();
  }

  public void set(Position position, String valueStr)
          throws IllegalArgumentException, IndexOutOfBoundsException {
    validate(position, valueStr);
    set(position, toAnother(valueStr));

    for (Position link : link.getOrDefault(position, new HashSet<>())) {
      calc(link);
    }
  }

  public void formula(Position position, String str)
          throws IllegalArgumentException, IndexOutOfBoundsException {
    validate(position, str);
    Formula formula = new Formula();

    if (!formula.setFromStr(str) || !isAddressOk(formula.getCells())) {
      System.out.println("Вывод: Ошибка!");
    } else if (!isRecursion(position, formula.getCells())) {
      System.out.println("Вывод: Транзитивность!");
    }
    set(position, formula);
    add(position, formula);
    calc(position);
  }

  private boolean isAddressCorrect(Position position) {
    if (position == null) {
      return true;
    }
    if (position.getRow() < 1 || position.getRow() > rows()) {
      return true;
    } else return position.getColumn() < 1 || position.getColumn() > columns();

  }

  private Cell get(Position position) {
    return spreadsheet.get(position.getRow() - 1).get(position.getColumn() - 1);
  }

  private void set(Position position, Cell value) {
    remove(position);
    spreadsheet.get(position.getRow() - 1).set(position.getColumn() - 1, value);
  }

  private void validate(Position position, String valueStr)
          throws IllegalArgumentException, IndexOutOfBoundsException {
    if (isAddressCorrect(position) || valueStr == null) {
      throw new IllegalArgumentException("Вывод: Ошибка!");
    }
  }

  private Cell toAnother(String value) {
    Cell result = new Str(value);
    Number intResult = new Number();

    if (intResult.setFromStr(value)) {
      result = intResult;
    }

    return result;
  }

  private void add(Position position, Formula formula) {
    for (Position cell : formula.getCells()) {
      HashSet<Position> addresses = link.getOrDefault(cell, new HashSet<>());
      addresses.add(position);
      link.put(cell, addresses);
    }
  }

  private void remove(Position position) {
    Cell currValue = get(position);
    if (currValue instanceof Formula) {
      Formula formula = (Formula) currValue;
      for (Position cell : formula.getCells()) {
        link.get(position).remove(cell);
      }
    }
  }

  private void calc(Position position) throws IllegalArgumentException {
    if (!(get(position) instanceof Formula)) {
      return;
    }
    Formula formula = (Formula) get(position);
    Stack<Cell> stack = new Stack<>();

    for (String token : formula.getTokens()) {
      if (Maths.isSign(token)) {
        if (Position.parseAttempt(token)) {
          stack.add(get(Position.analysis(token)));
        } else {
          stack.add(toAnother(token));
        }
        continue;
      }
      Cell first = stack.pop();
      Cell second = stack.pop();

      try {
        stack.push(Maths.result(first, second, token));
      } catch (Exception ex) {
        formula.setResult(null);
        return;
      }
    }
    formula.setResult(stack.peek());
  }

  private boolean isRecursion(Position start, HashSet<Position> addressTokens) {
    if (addressTokens.contains(start)) {
      return false;
    }

    HashSet<Position> passedCells = new HashSet<>();
    Stack<Position> cellsQueue = new Stack<>();
    cellsQueue.addAll(addressTokens);

    while (!cellsQueue.isEmpty()) {
      Position current = cellsQueue.pop();

      if (passedCells.contains(current)) {
        continue;
      }
      passedCells.add(current);
      Cell currentValue = get(current);

      if (!(currentValue instanceof Formula)) {
        continue;
      }
      Formula formula = (Formula) currentValue;

      for (Position cell : formula.getCells()) {
        if (cell.equals(start)) {
          return false;
        }
        cellsQueue.add(cell);
      }
    }

    return true;
  }

  private boolean isAddressOk(HashSet<Position> formulaAddresses) {
    for (Position position : formulaAddresses) {
      if (isAddressCorrect(position)) {
        return false;
      }
    }
    return true;
  }
}
