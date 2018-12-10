package Customer;

import Supermarket.Product;

import java.util.List;

public interface IBasket {
  List<Product> getProducts();
  void PickUpProduct(Product product);
}
