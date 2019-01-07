package com.company.Report;

import com.company.Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReportTest {
  private Product firstProduct = new Product("product#0", 1, 10, true);
  private Product secondProduct = new Product("product#0", 1, 10, true);
  private Product thirdProduct = new Product("product#1", 1, 10, true);
  private BigDecimal cashAmount = new BigDecimal(0);
  private List<Bill> billsList = new ArrayList<>();
  private Report report = new Report();

  @Test
  public void setBillsList() {
    List<Product> productsList = new ArrayList<>();
    productsList.add(firstProduct);
    productsList.add(secondProduct);
    productsList.add(thirdProduct);
    this.cashAmount = this.cashAmount.add((firstProduct.getPrice()).multiply(new BigDecimal(firstProduct.getCount())));
    this.cashAmount = this.cashAmount.add((secondProduct.getPrice()).multiply(new BigDecimal(secondProduct.getCount())));
    this.cashAmount = this.cashAmount.add((thirdProduct.getPrice()).multiply(new BigDecimal(thirdProduct.getCount())));
    Bill bill = new Bill(cashAmount, productsList);
    billsList.add(bill);
    report.setBillsList(bill);
    Assert.assertEquals(billsList.size(), 1);
    Assert.assertEquals(productsList.size(), 3);
    Assert.assertEquals(firstProduct.getCount().intValue(), 2);
    Assert.assertEquals(bill.getAmount().intValue(), 30);
  }

  @Test
  public void printReport() {
  }
}