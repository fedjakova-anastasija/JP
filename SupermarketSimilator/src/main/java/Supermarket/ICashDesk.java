package Supermarket;

import Customer.Customer;
import Discount.Discount;
import Report.Bill;

public interface ICashDesk {
  Bill getBill(Customer customer, Discount discount);

  Integer getAmount();
}
