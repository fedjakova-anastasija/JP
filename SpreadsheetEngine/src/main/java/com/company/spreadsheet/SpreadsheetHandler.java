package com.company.spreadsheet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SpreadsheetHandler {
    private Spreadsheet spreadsheet;

    public SpreadsheetHandler(Spreadsheet spreadsheet) throws Exception {
        this.spreadsheet = spreadsheet;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!line.equals("exit")) {
            System.out.print("Ввод: ");
            line = reader.readLine();
            String[] input = line.split(" ");

            if (!isInputCorrect(input)) continue;

            switch (input[0]) {
                case "set":
                    getAnswer(input,"set");
                    break;
                case "setformula":
                    getAnswer(input,"setformula");
                    break;
                case "display":
                    this.spreadsheet.display();
                    break;
            }
        }
    }

    private boolean isInputCorrect(String[] input) {
        try {
            if ((input[0].equals("set") || input[0].equals("setformula"))) {
                if (input.length < 3) {
                    throw new Exception("Возможные команды: set/setformula cell number/formula");
                }
                Cell cell = getCell(input[1]);
                if (input[1].length() < 2 || cell == null || cell.letter < 'A' || cell.letter > 'Z') {
                    throw new Exception("Возможные команды: set/setformula cell number/formula");
                }
            }
            if (!input[0].equals("display") && !input[0].equals("set") && !input[0].equals("setformula")) {
                    throw new Exception("Возможные команды: set/setformula cell number/formula");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    private void getAnswer(String[] input, String item) throws Exception {
        Cell cell = getCell(input[1]);
        if (item.equals("set")) {
            spreadsheet.setValue(cell, input[2]);
        }
        else {
            StringBuilder formula = new StringBuilder();
            for (int i = 2; i < input.length; ++i) {
                formula.append(input[i]).append(" ");
            }
            input[2] = formula.toString();
            List<String> items = Arrays.asList(input);
            items = items.subList(1, 3);
            if (!isCorrectFormula(items)) {
                throw new Exception("Возможные команды: set/setformula cell number/formula");
            }
            spreadsheet.setValue(cell, "formula " + items.get(1));

        }
        System.out.println("Вывод: OK");
    }

    private boolean isCorrectFormula(List<String> input) throws Exception {
        String formula = input.get(1).replaceAll("\\(|\\)|\\[|\\]", " ");
        String[] prefixes = formula.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = prefixes.length - 1; i > -1; i--) {
            String prefix = prefixes[i];
            if (prefix.equals("")) {
                continue;
            }
            if (prefix.equals("+") || prefix.equals("/") || prefix.equals("*")
                    || prefix.equals("-")) {
                if (stack.size() < 2) {
                    return false;
                }
                stack.push(stack.pop() + stack.pop());
            } else {
                if (input.get(0).equals(prefix)) {
                    return false;
                }

                if (!prefix.matches("[-+]?\\d+")) {
                    Cell cell = getCell(prefix);
                    if (spreadsheet.isFormula(cell)) {
                        List<String> items = new ArrayList<>();
                        items.add(input.get(0));
                        items.add(spreadsheet.getFormula(cell));

                        return isCorrectFormula(items);
                    }
                }
                stack.push(prefix);
            }
        }

        if ((stack.pop() != null) && (stack.size() != 0)) {
            return false;
        }

        spreadsheet.checkDivisionByZero(formula);
        input.set(1, formula);

        return true;
    }

    private Cell getCell(String variable) {
        Cell cell = new Cell();
        Character symbol = variable.charAt(0);
        String newStr = variable.substring(1);
        if (newStr.matches("[-+]?\\d+")){
            Integer number;
            number = Integer.parseInt(newStr);

            cell.letter = symbol;
            cell.position = number;
            return cell;
        }
        return null;
    }
}
