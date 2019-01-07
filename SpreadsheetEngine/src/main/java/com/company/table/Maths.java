package com.company.table;

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
    if (isNumber(left) && isNumber(right)) {
      float result = ((Number) left).get() + ((Number) right).get();
      return new Number(result);
    }
    else {
      String result = left.get().toString().trim() + right.get().toString().trim();
      return new Str(result);
    }
  }

  private static Cell sub(Cell left, Cell right) {
    if (isNumber(left) && isNumber(right)) {
      float result = ((Number) left).get() - ((Number) right).get();
      return new Number(result);
    }

    return null;
  }

  private static Cell mul(Cell left, Cell right) {
    if (isNumber(left) && isNumber(right)) {
      float result = ((Number) left).get() * ((Number) right).get();
      return new Number(result);
    }

    return null;
  }

  private static Cell div(Cell left, Cell right) throws IllegalArgumentException {
    if (isNumber(left) && isNumber(right)) {
      if (((Number) right).get() == 0) {
        System.out.println("Вывод: НА НОЛЬ ДЕЛИТЬ НЕЛЬЗЯ!");
        return null;
      }
      float result = ((Number) left).get() / ((Number) right).get();
      return new Number(result);
    }
    return null;
  }

  private static boolean isNumber(Cell value) {
    return value instanceof Number;
  }
}
