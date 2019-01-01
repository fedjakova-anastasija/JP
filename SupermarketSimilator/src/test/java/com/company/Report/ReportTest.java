package com.company.Report;

import com.company.Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReportTest {
  private Product firstProduct = new Product("product#0", 1, 10, true);
  private BigDecimal amount = new BigDecimal(0);
  private List<Bill> billsList = new ArrayList<>();
  private Report report = new Report();

  @Test
  public void setBillsList() {
    List<Product> productsList = new ArrayList<>();
    productsList.add(firstProduct);
    Bill bill = new Bill(amount, productsList);
    billsList.add(bill);
    report.setBillsList(bill);
    Assert.assertEquals(billsList.size(), 1);
  }

  @Test
  public void printReport() {
  }
}