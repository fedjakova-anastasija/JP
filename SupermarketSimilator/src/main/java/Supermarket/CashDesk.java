package Supermarket;

import Customer.Customer;
import Customer.CustomerType;
import Discount.Discount;
import Report.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashDesk {
    private BigDecimal cashAmount = new BigDecimal(0);
    private List<Discount> discountsList = new ArrayList<>();

    private Double getDiscountCoefficient(Customer customer, Product product) {
        double percent = 1;
        for (Discount discount : this.discountsList) {
            percent *= discount.getDiscountForRetired(customer, discount.getDiscount());
        }
        return percent;
    }

    Bill getBill(Customer customer) {
        this.cashAmount = new BigDecimal(0);
        if (!customer.getBasket().getProducts().isEmpty()) {
            for (Product product : customer.getBasket().getProducts()) {
                if (product.alcoholType() && customer.getType() == CustomerType.CHILD) {
                    System.out.println(LocalDateTime.now().withNano(0) + " THE CHILD IS TRYING TO BUY ALCOHOL!");
                    break;
                }
                Double discountCoefficient = getDiscountCoefficient(customer, product);
                Double price = product.getPrice().doubleValue() * discountCoefficient * 0.01;
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

    public void addDiscount(Double discount) {
        Discount addedDiscount = new Discount(discount);
        this.discountsList.add(addedDiscount);
    }
}
