package com.company;

import com.company.spreadsheet.Spreadsheet;
import com.company.spreadsheet.SpreadsheetHandler;

public class Main {
    public static void main(String[] args) {
        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            new SpreadsheetHandler(spreadsheet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}