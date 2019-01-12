package com.company.cell;

public abstract class Cell<Cell> {
  private Cell value;

  public final boolean setFromStr(String str) {
    if (!parseAttempt(str)) {
      return false;
    }
    value = analysis(str);
    return true;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  public Cell get() {
    return value;
  }

  final void set(Cell value) {
    this.value = value;
  }

  abstract Cell analysis(String str);

  private boolean parseAttempt(String str) {
    try {
      Cell val = analysis(str);

      if (val == null) {
        return false;
      }
    } catch (Exception ex) {
      return false;
    }
    return true;
  }
}
