package Discount;

import Customer.Customer;
import Customer.CustomerType;

public class Discount {
    private Double discount;

    public Discount(Double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public double getDiscountForRetired(Customer customer, double discount) {
        if (customer.getType().equals(CustomerType.RETIRED))
            return 100 - discount;
        return 100;
    }
}
