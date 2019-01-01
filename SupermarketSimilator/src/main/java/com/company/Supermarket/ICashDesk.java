package com.company.Supermarket;

import com.company.Customer.Customer;
import com.company.Discount.Discount;
import com.company.Report.Bill;

public interface ICashDesk {
  Bill getBill(Customer customer, Discount discount);

  Integer getAmount();
}
