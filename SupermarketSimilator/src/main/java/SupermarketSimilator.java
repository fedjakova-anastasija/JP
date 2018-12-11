import Customer.Customer;
import Customer.CustomerType;
import Discount.Discount;
import Supermarket.Product;
import Supermarket.Supermarket;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SupermarketSimilator {

  public static void main(String[] args) throws CloneNotSupportedException {
    List<String> productTypes = Arrays.asList("product#0", "product#1", "product#2", "product#3", "product#4", "product#5");
    List<String> customerTypes = Arrays.asList("child", "adult", "retired");
    List<String> paymentMethods = Arrays.asList("card", "cash", "bonuses");

    System.out.println(LocalDateTime.now().withNano(0) + " Supermarket products have been formed:");
    Random random = new Random();
    Supermarket supermarket = new Supermarket();
    boolean alcoholType = false;
    for (String element : productTypes) {
      if (productTypes.indexOf(element) == 1) {
        alcoholType = true;
      }
      Integer units = random.nextInt(50) + 1;
      Integer price = random.nextInt(100) + 1;
      System.out.println(LocalDateTime.now().withNano(0).withNano(0) + "  ------------------------------------------");
      System.out.println(LocalDateTime.now().withNano(0).withNano(0) + " |  " + element + "  |  units: " + units + "  |  price: " + price + "  |");
      supermarket.addProduct(element, units, price, alcoholType);
    }
    System.out.println(LocalDateTime.now().withNano(0).withNano(0) + "  ------------------------------------------");
    System.out.println(LocalDateTime.now().withNano(0) + " SUPERMARKET IS OPENED");

    supermarket.addCustomer(customerTypes.get(random.nextInt(3)), paymentMethods.get(random.nextInt(3)));

    double discount = ((random.nextInt(50) + 1));
    String productValue = supermarket.getProduct(random.nextInt(supermarket.getProductsCount())).getType();
    Discount newDiscount = new Discount(discount, productValue);
    System.out.println(LocalDateTime.now().withNano(0) + " DISCOUNT FOR RETIRED " + discount + "% on " + productValue);

    while (isSuperMarketNotEmpty(supermarket)) {
      if (random.nextInt(supermarket.getCustomersCount()) > 0 || supermarket.getCustomersCount() > 2) {
        int productId = random.nextInt(supermarket.getProductsCount() - 1);
        Product product = supermarket.getProduct(productId);
        Integer customerId = random.nextInt(supermarket.getCustomersCount() - 1);
        Customer customer = supermarket.getCustomer(customerId);
        while (customer == null) {
          customerId++;
          if (customerId >= supermarket.getCustomersCount())
            customerId = 0;
          customer = supermarket.getCustomer(customerId);
        }
        Integer units = random.nextInt(4) + 1;
        if (product.getCount() >= units) {
          supermarket.changeProductCount(product.getCount() - units, productId);
          product.setCount(units);
        } else {
          System.out.println(LocalDateTime.now().withNano(0) + " Customer#" + customerId + " tries to pick up " + product.getType() + " more than he can");
          continue;
        }
        if (product.alcoholType() && customer.getType() == CustomerType.CHILD) {
          System.out.println(LocalDateTime.now().withNano(0) + " THE CHILD IS TRYING TO BUY ALCOHOL!");
        } else {
          customer.ChooseProduct(product);
          System.out.println(LocalDateTime.now().withNano(0) + " Customer " + customer.getType().toString().toLowerCase() + " 'customer#" + customerId + "' picked up " + product.getCount() + " units of " + product.getType());
        }
      } else {
        supermarket.addCustomer(customerTypes.get(random.nextInt(3)), paymentMethods.get(random.nextInt(3)));
      }
      for (Integer j = 0; j < supermarket.getCustomersCount(); ++j) {
        Customer customer = supermarket.getCustomer(j);
        if (customer != null) {
          if (supermarket.getCustomer(j).getBasket().getProducts().size() > random.nextInt(2)) {
            supermarket.ToCashDesk(j, newDiscount);
            System.out.println(LocalDateTime.now().withNano(0) + " Customer " + customer.getType().toString().toLowerCase() + " 'customer#" + j + "' at the cash desk, amount to pay: " + supermarket.getCashDesk().getAmount());
            System.out.println(LocalDateTime.now().withNano(0) + " Customer " + customer.getType().toString().toLowerCase() + " 'customer#" + j + "' paid " + supermarket.getCashDesk().getAmount() + " by " + customer.getPaymentMethod().toString().toLowerCase());

          }
        }
      }
    }
    System.out.println(LocalDateTime.now().withNano(0) + " SUPERMARKET IS CLOSED");
    supermarket.getReport().printReport();
  }

  private static Boolean isSuperMarketNotEmpty(Supermarket supermarket) {
    for (int i = 0; i < supermarket.getCustomersCount(); ++i) {
      if (supermarket.getCustomer(i) != null)
        return true;
    }
    return false;
  }
}
