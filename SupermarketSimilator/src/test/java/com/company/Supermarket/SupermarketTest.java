package com.company.Supermarket;

import com.company.Customer.Customer;
import com.company.Discount.Discount;
import org.junit.Assert;
import org.junit.Test;

public class SupermarketTest {
  private Customer firstCustomer = new Customer("adult", "cash");
  private Supermarket supermarket = new Supermarket();

  @Test
  public void getCustomer() throws CloneNotSupportedException {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    supermarket.addCustomer("adult", "card");
    Customer customer = supermarket.getCustomer(0);
    customer.ChooseProduct(product);
    Assert.assertEquals("" + customer.getBasket().getProducts().size(), "1");
  }

  @Test
  public void getCustomersCount() {
    Assert.assertEquals(this.supermarket.getCustomersCount().intValue(), 0);
  }

  @Test
  public void getProductsCount() {
    Assert.assertEquals(this.supermarket.getProductsCount().intValue(), 0);
  }

  @Test
  public void getCashDesk() throws CloneNotSupportedException {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    Discount newDiscount = new Discount(10.0, "product#0");
    supermarket.addCustomer("adult", "card");
    Customer customer = supermarket.getCustomer(0);
    customer.ChooseProduct(product);
    supermarket.ToCashDesk(0, newDiscount);
    Assert.assertEquals("" + this.supermarket.getCashDesk().getAmount(), "10");
  }

  @Test
  public void getReport() throws CloneNotSupportedException {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    Discount newDiscount = new Discount(10.0, "product#0");
    supermarket.addCustomer("adult", "card");
    Customer customer = supermarket.getCustomer(0);
    customer.ChooseProduct(product);
    supermarket.ToCashDesk(0, newDiscount);
    supermarket.getReport().printReport();
    Assert.assertEquals("" + this.supermarket.getCashDesk().getAmount(), "10");
  }

  @Test
  public void addProduct() throws CloneNotSupportedException {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    this.firstCustomer.ChooseProduct(product);
    Assert.assertEquals((long) this.firstCustomer.getBasket().getProducts().size(), 1);
  }

  @Test
  public void addCustomer() throws CloneNotSupportedException {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    supermarket.addCustomer("adult", "card");
    Customer customer = supermarket.getCustomer(0);
    customer.ChooseProduct(product);
    Assert.assertEquals((long) customer.getBasket().getProducts().size(), 1);
  }

  @Test
  public void changeProductCount() {
    supermarket.addProduct("product#0", 1, 10, false);
    Product product = supermarket.getProduct(0);
    Integer units = 1;
    supermarket.changeProductCount(product.getCount() - units, 0);
    Assert.assertEquals(product.getCount().intValue(), 0);
  }
}