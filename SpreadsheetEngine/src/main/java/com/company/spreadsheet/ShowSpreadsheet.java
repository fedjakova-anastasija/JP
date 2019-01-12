package com.company.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public final class ShowSpreadsheet {
    private static final int WIDTH = 15;

    public static void show(Spreadsheet spreadsheet) {
        final int colsCount = spreadsheet.columns() + 1;
        System.out.print(toCellValue(""));

        for (Object value : toAddresses(colsCount)) {
            System.out.print(toCellValue(value));
        }
        System.out.print('\n');
        for (int i = 0; i < spreadsheet.rows(); ++i) {
            System.out.print(toCellValue(Integer.toString(i + 1)));

            for (Object value : spreadsheet.getTable().get(i).toArray()) {
                System.out.print(toCellValue(value));
            }
            System.out.print('\n');
        }
    }

    private static String toCellValue(Object value) {
        String result = value.toString();

        if (result.length() > WIDTH) {
            result = result.substring(0, WIDTH) + WIDTH;
        }

        StringBuilder builder = new StringBuilder(result);

        while (builder.length() < WIDTH) {
            builder.append(' ');
        }

        return builder.toString();
    }

    private static Object[] toAddresses(int finish) {
        int begin = 1;

        if (begin > finish) {
            finish = begin;
        }

        List<Object> result = new ArrayList<>();

        for (;begin < finish; ++begin) {
            result.add(Position.columnToString(begin));
        }

        return result.toArray();
    }
}
