package com.company.Discount;

import com.company.Customer.Customer;
import com.company.Customer.CustomerType;

public class Discount implements IDiscount {
    private Double discount;
    private String  product;

    public Discount(Double discount, String product) {
        this.discount = discount;
        this.product = product;
    }

    public double getDiscount() {
        return this.discount;
    }

    public String getProductType() {
        return this.product;
    }

    public double getDiscountForRetired(Customer customer, double discount) {
        if (customer.getType().equals(CustomerType.RETIRED)) {
            return 1 - 0.01 * discount;
        }
        return 1;
    }
}
