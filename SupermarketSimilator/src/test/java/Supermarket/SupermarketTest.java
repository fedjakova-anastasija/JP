package Supermarket;

import Customer.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SupermarketTest {
  private List<Customer> firstCustomersList = new ArrayList<>();
  private List<Customer> secondCustomersList = new ArrayList<>();
  private Customer firstCustomer = new Customer("adult", "cash");
  private Supermarket supermarket = new Supermarket();

  @Test
  public void getCustomer() {
    Assert.assertEquals(this.supermarket.getCustomer(0).toString(), "");
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
  public void getProduct() {
  }

  @Test
  public void getCashDesk() {
  }

  @Test
  public void getReport() {
  }

  @Test
  public void addProduct() {
    supermarket.addProduct("product#0", 1, 10, false);
    Assert.assertEquals(supermarket.getProductsCount().intValue(), 1);
  }

  @Test
  public void addCustomer() {
    supermarket.addCustomer("product#0", "card");
    Assert.assertEquals(supermarket.getCustomersCount().intValue(), 1);
  }

  @Test
  public void changeProductCount() {
  }

  @Test
  public void toCashDesk() {
  }
}