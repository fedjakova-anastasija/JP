package com.company.Discount;

import com.company.Customer.Customer;

public interface IDiscount {
  double getDiscountForRetired(Customer customer, double discount);
  String getProductType();
}
