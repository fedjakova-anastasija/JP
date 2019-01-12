package com.company.spreadsheet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
    private final int column;
    private final int row;

    public static Position analysis(String str) throws IllegalArgumentException {
        final Pattern pattern = Pattern.compile("( *)([a-zA-Z]+)([0-9]+)( *)");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid cell address format");
        }

        try {
            final int column = columnToNumber(matcher.group(2));
            final int row = Integer.parseUnsignedInt(matcher.group(3));
            return new Position(column, row);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid cell address format");
        }
    }

    public static boolean parseAttempt(String str) {
        try {
            analysis(str);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return getColumnStr() + row;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Position) {
            Position position = (Position) object;
            return position.row == row && position.column == column;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 31 * (31 * 31 + column) + row;
    }

    static String columnToString(int column) throws IllegalArgumentException {
        if (column < 1) {
            throw new IllegalArgumentException("Invalid columnumn value");
        }

        StringBuilder resultStr = new StringBuilder();
        final String str = Integer.toString(column, 27);

        for (int i = 0; i < str.length(); ++i) {
            final char ch = str.charAt(i);
            final String chStr = String.valueOf(ch);
            final int offset = Integer.parseInt(chStr, 27);
            final int code = (i == 0) ? 'A' + offset - 1 : 'A' + offset;
            resultStr.append(Character.toChars(code)[0]);
        }

        return resultStr.toString();
    }

    private static int columnToNumber(String column) throws IllegalArgumentException {
        if (column == null || column.isEmpty()) {
            throw new IllegalArgumentException("Invalid columnumn format");
        }
        StringBuilder resultStr = new StringBuilder();

        for (int i = 0; i < column.length(); ++i) {
            if (Character.isDigit(column.charAt(i))) {
                throw new IllegalArgumentException("Invalid columnumn format");
            }
            char ch = Character.toLowerCase(column.charAt(i));

            if (ch >= 'a' && ch <= 'i') {
                final int offset = ch - 'a';
                ch = Character.toChars('1' + offset)[0];
            } else {
                ch = Character.toChars('a' + ch - 'i' - 1)[0];
            }
            resultStr.append(ch);
        }

        return Integer.parseUnsignedInt(resultStr.toString(), 26);
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

    private String getColumnStr() {
        return columnToString(getColumn());
    }
}
