package com.company.operation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation implements OperationCollection {
  private OperationType operationType = OperationType.ERROR;
  private String spreadsheetItem = "";
  private String spreadsheetValue = "";

  public static OperationCollection parse(String line) {
    Operation operation = new Operation();

    if (line == null) {
      return operation;
    }
    if (line.equals("")) {
      return operation;
    }
    try {
      operation.parseOperation(line);
      if (operation.operationType == OperationType.ERROR) {
        throw new Exception("Вывод: Ошибка!");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

    return operation;
  }

  public OperationType getOperationType() {
    return operationType;
  }

  public String getSpreadsheetItem() {
    return spreadsheetItem;
  }

  public String getSpreadsheetValue() {
    return spreadsheetValue;
  }

  private void parseOperation(String input) {
    Matcher matcher = Pattern.compile(" *([\\S]+) *([a-zA-Z0-9]+)* *([\\S ]+)* *").matcher(input);
    matcher.find();
    operationType = OperationType.ERROR;
    switch (matcher.group(1)) {
      case "set":
        operationType = OperationType.SET;
        break;
      case "setformula":
        operationType = OperationType.SETFORMULA;
        break;
      case "display":
        operationType = OperationType.DISPLAY;
        break;
    }
    spreadsheetItem = matcher.group(2);
    spreadsheetValue = matcher.group(3);
  }
}
