package com.company.cell;

import com.company.spreadsheet.Position;
import com.company.spreadsheet.Maths;

import java.util.*;

public final class Formula extends Cell<Cell> {
  public static final String ERROR = "❌";

  private final List<String> tokens = new ArrayList<>();
  private final HashSet<Position> cells = new HashSet<>();

  public List<String> getTokens() {
    return tokens;
  }

  public HashSet<Position> getCells() {
    return cells;
  }

  public void setResult(Cell value) {
    set(value);
  }

  @Override
  public Cell get() {
    return super.get() == null ? new Str(ERROR) : super.get();
  }

  @Override
  public String toString() {
    return get() == null ? ERROR : get().toString();
  }

  @Override
  protected Cell analysis(String str) throws IllegalArgumentException {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < str.length(); ++i) {
      final char ch = str.charAt(i);
      result.append((ch != '(' && ch != ')') ? ch : ' ');
    }
    StringTokenizer tokenizer = new StringTokenizer(result.toString());
    Stack<String> stack = new Stack<>();
    tokens.clear();
    cells.clear();

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      Number number = new Number();

      if (Maths.isSign(token) && !number.setFromStr(token) && Position.parseAttempt(token)) {
        cells.add(Position.analysis(token));
      }
      stack.add(token);
    }
    while (!stack.empty()) {
      tokens.add(stack.pop());
    }
    if (!isCorrect()) {
      tokens.clear();
      cells.clear();
      return null;
    }

    return new Number();
  }

  private boolean isCorrect() {
    Stack<String> stack = new Stack<>();

    for (String token : getTokens()) {
      if (Maths.isSign(token)) {
        stack.add(token);
        continue;
      }
      if (stack.size() < 2) {
        return false;
      }
      stack.pop();
      stack.pop();
      stack.push("");
    }

    return stack.size() == 1;
  }
}
