package com.company.spreadsheet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SpreadsheetHandler {
    private Spreadsheet spreadsheet;
    private HashMap<String, Command> commands;

    public SpreadsheetHandler(Spreadsheet spreadsheet) throws Exception {
        this.spreadsheet = spreadsheet;
        commands = new HashMap<>();
        commands.put("set", this::setValue);
        commands.put("setformula", this::setFormula);
        commands.put("display", input -> this.spreadsheet.display());

        this.start();
    }

    private void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!line.equals("exit")) {
            System.out.print("Ввод: ");
            line = reader.readLine();
            String[] input = line.split(" ");

            if ((input[0].equals("set") || input[0].equals("setformula"))) {
                if (input.length < 3)
                {
                    System.out.println("Возможные команды: set/setformula cell number/formula");
                    continue;
                }
                PairStruct pair = getVariableInfo(input[1]);
                if (input[1].length() < 2 || pair.symbol < 'A' || pair.symbol > 'Z') {
                    System.out.println("Возможные команды: set/setformula cell number/formula");
                    continue;
                }
            }

            if (input[0].equals("setformula")) {
                PairStruct pair = getVariableInfo(input[1]);
                List<String> items = Arrays.asList(input);
                items = items.subList(1, 3);
                if (!isCorrectFormula(items)) {
                    throw new Exception("[ERR] wrong formula");
                }
                spreadsheet.setValue(pair, "formula " + items.get(1));

                StringBuilder formula = new StringBuilder();
                for (int i = 2; i < input.length; ++i) {
                    formula.append(input[i]).append(" ");
                }
                input[2] = formula.toString();
            }

            if (input[0].equals("set")) {
                PairStruct pair = getVariableInfo(input[1]);
                spreadsheet.setValue(pair, input[2]);
                System.out.println("Вывод: OK");
            }

            if (commands.containsKey(input[0])) {
                commands.get(input[0]).runCommand(input);
            } else {
                System.out.println("Возможные команды: set/setformula/display/exit");
            }
        }
    }

    private void setValue(String[] input) throws Exception {
       /* PairStruct pair = getVariableInfo(input[1]);
        spreadsheet.setValue(pair, input[2]);
        System.out.println("Вывод: OK");*/
    }

    private void setFormula(String[] input) throws Exception {
       /* PairStruct pair = getVariableInfo(input[1]);
        List<String> items = Arrays.asList(input);
        items = items.subList(1, 3);
        if (!isCorrectFormula(items)) {
            throw new Exception("[ERR] wrong formula");
        }
        spreadsheet.setValue(pair, "formula " + items.get(1));
        System.out.println("Вывод: OK");*/
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isCorrectFormula(List<String> input) throws Exception {
        String formula = input.get(1).replaceAll("\\(|\\)|\\[|\\]", " ");
        String[] prefixes = formula.split(" ");
        Stack<String> stack = new Stack<String>();
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
                if (!isDouble(prefix)) {
                    PairStruct cell = getVariableInfo(prefix);
                    if (spreadsheet.isFormula(cell)) {
                        List<String> items = new ArrayList<String>();
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

    private PairStruct getVariableInfo(String variable) {
        PairStruct pair = new PairStruct();
        Character symbol = variable.charAt(0);
        String newStr = variable.substring(1, variable.length());

        Integer number;
        number = Integer.parseInt(newStr);

        pair.symbol = symbol;
        pair.number = number;
        return pair;
    }
}
