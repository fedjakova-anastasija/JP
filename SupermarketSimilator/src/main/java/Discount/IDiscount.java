package Discount;

import Customer.Customer;

public interface IDiscount {
  double getDiscountForRetired(Customer customer, double discount);
  String getProductType();
}
