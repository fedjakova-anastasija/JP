package Customer;

import PaymentMethod.PaymentType;
import PaymentMethod.PaymentMethod;
import Supermarket.Product;

public class Customer {
    private CustomerType customerType;
    private PaymentMethod paymentMethod;
    private Basket basket = new Basket();

    public Customer(String customerType, String method) {
        if (customerType.toLowerCase().equals("child")) {
            this.customerType = CustomerType.CHILD;
        } else {
            if (customerType.toLowerCase().equals("adult")) {
                this.customerType = CustomerType.ADULT;
            } else {
                if (customerType.toLowerCase().equals("retired")) {
                    this.customerType = CustomerType.RETIRED;
                }
            }
        }
        this.paymentMethod = new PaymentMethod(method);
    }

    public PaymentType getPaymentMethod() {
        return this.paymentMethod.getPaymentType();
    }

    public CustomerType getType() {
        return this.customerType;
    }

    public Basket getBasket() {
        return this.basket;
    }

    public void ChooseProduct(Product product) {
        for (Product chosenProduct : this.basket.getProducts()) {
            if (chosenProduct.getType().equals(product.getType())) {
                chosenProduct.setCount(chosenProduct.getCount() + product.getCount());
                return;
            }
        }
       // this.basket.PickUpProduct(product);
        try {
            this.basket.PickUpProduct(product.clone());
        }
        catch(CloneNotSupportedException ex){

            System.out.println("Supermarket.Product clone is not supported");
        }
    }
}
