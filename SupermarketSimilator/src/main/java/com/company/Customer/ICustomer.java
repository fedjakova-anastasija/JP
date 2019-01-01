package com.company.Customer;

import com.company.PaymentMethod.PaymentType;
import com.company.Supermarket.Product;

public interface ICustomer {
  PaymentType getPaymentMethod();

  CustomerType getType();

  Basket getBasket();

  void ChooseProduct(Product product) throws CloneNotSupportedException;
}
