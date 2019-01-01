package com.company.Supermarket;

import com.company.Customer.Customer;
import com.company.Discount.Discount;
import org.junit.Assert;
import org.junit.Test;

public class CashDeskTest {
  private Product firstProduct = new Product("product#0", 1, 10, false);
  private Product secondProduct = new Product("product#1", 1, 10, false);
  private CashDesk firstCashDesk = new CashDesk();
  private CashDesk secondCashDesk = new CashDesk();
  private CashDesk thirdCashDesk = new CashDesk();
  private Customer firstCustomer = new Customer("adult", "cash");
  private Customer secondCustomer = new Customer("child", "cash");
  private Customer thirdCustomer = new Customer("retired", "bonuses");

  @Test
  public void getAmount() throws CloneNotSupportedException {
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    firstCustomer.ChooseProduct(firstProduct);
    secondCustomer.ChooseProduct(secondProduct);
    firstCashDesk.getBill(firstCustomer, newDiscount);
    secondCashDesk.getBill(secondCustomer, newDiscount);
    thirdCashDesk.getBill(thirdCustomer, newDiscount);
    Assert.assertEquals("" + firstCashDesk.getAmount(), "10");
    Assert.assertEquals("" + secondCashDesk.getAmount(), "10");
    Assert.assertEquals("" + thirdCashDesk.getAmount(), "0");
    //Assert.assertNotEquals("" + firstCashDesk.getAmount(), "10");
  }
}