package com.company.spreadsheet;

import java.util.*;

public class Spreadsheet {
    private static TreeMap<Character, TreeMap<Integer, String>> table = new TreeMap<>();
    private static SpreadsheetViewer viewer = new SpreadsheetViewer(table);

    public Spreadsheet() {
    }

    void setValue(Cell pair, String newValue) {
        if (table.containsKey(pair.letter)) {
            TreeMap<Integer, String> row = table.get(pair.letter);
            row.put(pair.position, newValue);
        } else {
            TreeMap<Integer, String> row = new TreeMap<Integer, String>();
            row.put(pair.position, newValue);
            table.put(pair.letter, row);
        }
    }


    boolean isFormula(Cell cell) {
        boolean result = false;
        if (table.containsKey(cell.letter)) {
            if (table.get(cell.letter).containsKey(cell.position)) {
                String[] input = table.get(cell.letter).get(cell.position).split(" ");
                result = input[0].equals("formula");
            }
        }
        return result;
    }

    String getFormula(Cell cell) {
        String str = "";
        if (table.containsKey(cell.letter)) {
            if (table.get(cell.letter).containsKey(cell.position)) {
                String[] input = table.get(cell.letter).get(cell.position).split(" ");
                str = input[1];
            }
        }
        return str;
    }

    void display() throws Exception {
        viewer.display();
    }

    void checkDivisionByZero(String value) throws Exception {
        viewer.checkDivisionByZero(value);
    }
}
