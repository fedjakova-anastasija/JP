package com.company.Discount;

import com.company.Customer.Customer;
import org.junit.Assert;
import org.junit.Test;

public class DiscountTest {
  @Test
  public void getDiscount() {
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    Assert.assertEquals("" + newDiscount.getDiscount(), "10.0");
  }

  @Test
  public void getProductType() {
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    Assert.assertEquals(newDiscount.getProductType(), "product#0");
  }

  @Test
  public void getDiscountForRetired() {
    Customer firstCustomer = new Customer("child", "card");
    Customer secondCustomer = new Customer("retired", "bonuses");
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    Assert.assertEquals("" + newDiscount.getDiscountForRetired(firstCustomer, discount), "1.0");
    Assert.assertEquals("" + newDiscount.getDiscountForRetired(secondCustomer, discount), "0.9");
  }
}