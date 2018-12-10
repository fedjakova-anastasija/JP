package Supermarket;

import Customer.Customer;
import org.junit.Test;

public class CashDeskTest {
  private Product firstProduct = new Product("product#0", 1, 10, true);
  private CashDesk firstCashDesk = new CashDesk();
  private CashDesk secondCashDesk = new CashDesk();
  private Customer firstCustomer = new Customer("adult", "cash");
  private Customer secondCustomer = new Customer("adult", "cash");

  @Test
  public void getBill() {
  }

/*
  @Test
  public void getAmount() throws CloneNotSupportedException {
    this.firstCustomer.ChooseProduct(this.firstProduct);
    this.firstCashDesk.getBill(firstCustomer);
    this.secondCashDesk.getBill(secondCustomer);
    Assert.assertEquals(firstCashDesk.getAmount().intValue(), 10);
    Assert.assertEquals(secondCashDesk.getAmount().intValue(), 0);
  }
*/

  @Test
  public void addDiscount() {
  }
}