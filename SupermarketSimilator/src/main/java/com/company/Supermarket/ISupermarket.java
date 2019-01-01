package com.company.Supermarket;

import com.company.Customer.Customer;
import com.company.Discount.Discount;
import com.company.Report.Report;

interface ISupermarket {
  Customer getCustomer(Integer id);

  Integer getCustomersCount();

  Integer getProductsCount();

  Product getProduct(Integer id);

  CashDesk getCashDesk();

  Report getReport();

  void addProduct(String productType, Integer count, Integer productPrice, boolean alcoholType);

  void addCustomer(String productType, String paymentMethod);

  void changeProductCount(int count, int id);

  void ToCashDesk(Integer customerIndex, Discount discount);
}
