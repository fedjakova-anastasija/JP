package com.company.Supermarket;

import java.math.BigDecimal;

public interface IProduct {
  Integer getCount();

  BigDecimal getPrice();

  String getType();

  void setCount(Integer count);

  void setPrice(Integer price);

  Product clone() throws CloneNotSupportedException;

  Boolean alcoholType();
}
