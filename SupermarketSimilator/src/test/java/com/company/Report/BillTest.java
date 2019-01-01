package com.company.Report;

import com.company.Discount.Discount;
import com.company.Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BillTest {
  private Product firstProduct = new Product("product#0", 1, 10, true);
  private Product secondProduct = new Product("product#1", 2, 20, true);
  private Product thirdProduct = new Product("product#2", 3, 30, false);
  private BigDecimal amount = new BigDecimal(0);

  @Test
  public void getProductsList() {
    List<Product> productsList = new ArrayList<>();
    productsList.add(firstProduct);
    productsList.add(secondProduct);
    productsList.add(thirdProduct);
    Bill bill = new Bill(amount, productsList);
    Assert.assertEquals(bill.getProductsList().size(), 3);
  }

  @Test
  public void getAmount() {
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    List<Product> productsList = new ArrayList<>();
    productsList.add(firstProduct);
    productsList.add(secondProduct);
    productsList.add(thirdProduct);
    this.amount = this.amount.add((firstProduct.getPrice()));
    this.amount = this.amount.add((secondProduct.getPrice()));
    this.amount = this.amount.add((thirdProduct.getPrice()));
    Bill bill = new Bill(amount, productsList);
    Assert.assertEquals(bill.getAmount().intValue(), 60);
  }
}