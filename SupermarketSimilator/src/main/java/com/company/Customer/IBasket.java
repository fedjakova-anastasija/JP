package com.company.Customer;

import com.company.Supermarket.Product;

import java.util.List;

public interface IBasket {
  List<Product> getProducts();
  void PickUpProduct(Product product);
}
