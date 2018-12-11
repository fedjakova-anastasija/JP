package Supermarket;

import Customer.Customer;
import Discount.Discount;
import Report.Bill;

import java.math.BigDecimal;

public class CashDesk implements ICashDesk {
    private BigDecimal cashAmount = new BigDecimal(0);

    public Bill getBill(Customer customer, Discount discount) {
        this.cashAmount = new BigDecimal(0);
        if (!customer.getBasket().getProducts().isEmpty()) {
            for (Product product : customer.getBasket().getProducts()) {
                double discountCoefficient = 1;
                if (discount.getProductType().equals(product.getType())) {
                    discountCoefficient *= discount.getDiscountForRetired(customer, discount.getDiscount());
                }
                Double price = product.getPrice().doubleValue() * discountCoefficient;
                int productIndex = customer.getBasket().getProducts().indexOf(product);
                product.setPrice(price.intValue());
                customer.getBasket().getProducts().set(productIndex, product);
                this.cashAmount = this.cashAmount.add((product.getPrice()).multiply(new BigDecimal(product.getCount())));
            }
        } else {
            this.cashAmount = new BigDecimal(0);
        }
        return new Bill(this.cashAmount, customer.getBasket().getProducts());
    }

    public Integer getAmount() {
        return this.cashAmount.intValue();
    }
}
