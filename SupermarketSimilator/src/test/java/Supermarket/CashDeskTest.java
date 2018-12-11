package Supermarket;

import Customer.Customer;
import Discount.Discount;
import org.junit.Assert;
import org.junit.Test;

public class CashDeskTest {
  private Product firstProduct = new Product("product#0", 1, 10, false);
  private CashDesk firstCashDesk = new CashDesk();
  private CashDesk secondCashDesk = new CashDesk();
  private Customer firstCustomer = new Customer("adult", "cash");
  private Customer secondCustomer = new Customer("child", "cash");

  @Test
  public void getAmount() throws CloneNotSupportedException {
    double discount = 10.0;
    String  product = "product#0";
    Discount newDiscount = new Discount(discount, product);
    firstCustomer.ChooseProduct(firstProduct);
    firstCashDesk.getBill(firstCustomer, newDiscount);
    secondCashDesk.getBill(secondCustomer, newDiscount);
    Assert.assertEquals("" + firstCashDesk.getAmount(), "10");
    Assert.assertEquals("" + secondCashDesk.getAmount(), "0");
  }
}