package com.company;

import com.company.operation.Operation;
import com.company.operation.OperationCollection;
import com.company.table.Position;
import com.company.table.ShowSpreadsheet;
import com.company.table.Spreadsheet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SpreadsheetEngine {
  private static final Position POSITION = new Position(5, 5);
  private static final Spreadsheet spreadsheet = new Spreadsheet(POSITION);

  public static void main(String[] args) {
    try {
      InputStreamReader stream = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(stream);
      String line;
      System.out.print("Ввод: ");

      while (!(line = reader.readLine()).equals("exit")) {
        OperationCollection command = Operation.parse(line);
        String str;
        switch (command.getOperationType()) {
          case SET:
            str = "set";
            getAnswer(command.getSpreadsheetItem(), command.getSpreadsheetValue(), str);
            break;
          case SETFORMULA:
            str = "setformula";
            getAnswer(command.getSpreadsheetItem(), command.getSpreadsheetValue(), str);
            break;
          case DISPLAY:
            ShowSpreadsheet.show(spreadsheet);
            break;
        }
        System.out.print("Ввод: ");
      }
      System.out.print("Вывод: OK");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  private static void getAnswer(String position, String input, String command) {
    try {
      if (!Position.parseAttempt(position)) {
        throw new Exception("Вывод: Ошибка!");
      }
      if (command.equals("set")) {
        spreadsheet.set(Position.analysis(position), input);
      }
      else {
        spreadsheet.formula(Position.analysis(position), input);
      }
      System.out.println("Вывод: OK");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
