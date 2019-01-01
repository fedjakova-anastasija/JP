package com.company.Report;

import com.company.Supermarket.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IBill {
  List<Product> getProductsList();

  BigDecimal getAmount();
}
