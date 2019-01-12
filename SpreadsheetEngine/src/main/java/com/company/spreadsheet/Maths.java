package com.company.spreadsheet;

import com.company.cell.Cell;
import com.company.cell.Formula;
import com.company.cell.Number;
import com.company.cell.Str;

import java.util.regex.Pattern;

public final class Maths {
  static Cell result(Cell a, Cell b, String operator) {
    switch (operator) {
      case "+":
        return sum(a, b);
      case "-":
        return sub(a, b);
      case "*":
        return mul(a, b);
      case "/":
        return div(a, b);
    }
    return new Str(Formula.ERROR);
  }

  public static boolean isSign(String token) {
    return !Pattern.compile("[-+*/]").matcher(token).matches();
  }

  private static Cell sum(Cell left, Cell right) {
    /*if (left.get() == null) {
      return right;
    }
    if (right.get() == null) {
      return left;
    }*/
    if (isNumber(left) && isNumber(right)) {
      float result = ((Number) left).get() + ((Number) right).get();
      return new Number(result);
    } else {
      String result = left.get().toString().trim() + right.get().toString().trim();
      return new Str(result);
    }
  }

  private static Cell sub(Cell left, Cell right) {
    try {
      if (isNumber(left) && isNumber(right)) {
        float result = ((Number) left).get() - ((Number) right).get();
        return new Number(result);
      } else {
        throw new Exception("Вывод: Строки можно только конкатенировать!");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

  private static Cell mul(Cell left, Cell right) {
    try {
      if (isNumber(left) && isNumber(right)) {
        float result = ((Number) left).get() * ((Number) right).get();
        return new Number(result);
      } else {
        throw new Exception("Вывод: Строки можно только конкатенировать!");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

  private static Cell div(Cell left, Cell right) throws IllegalArgumentException {
    try {
      if (isNumber(left) && isNumber(right)) {
        if (((Number) right).get() == 0) {
          throw new Exception("Вывод: На ноль делить нельзя!");
        }
        float result = ((Number) left).get() / ((Number) right).get();
        return new Number(result);
      } else {
        throw new Exception("Вывод: Строки можно только конкатенировать!");
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return null;
  }

  private static boolean isNumber(Cell value) {
    return value instanceof Number;
  }
}
