package Customer;

import PaymentMethod.PaymentType;
import Supermarket.Product;

public interface ICustomer {
  PaymentType getPaymentMethod();

  CustomerType getType();

  Basket getBasket();

  void ChooseProduct(Product product) throws CloneNotSupportedException;
}
