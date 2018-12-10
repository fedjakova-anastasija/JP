package Report;

import Supermarket.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Report implements IReport {
    private List<Bill> billsList = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);

    public void setBillsList(Bill bill) {
        this.billsList.add(bill);
    }

    public void printReport() {
        System.out.println(LocalDateTime.now().withNano(0) + " REPORT:");
        for (Bill item : this.billsList) {
            System.out.println(LocalDateTime.now().withNano(0) + " SUM " + item.getAmount() + " of " + "BILL#" + ": ");
            for (Product product : item.getProductsList()) {
                System.out.println(LocalDateTime.now().withNano(0) + " " + product.getCount() + " unit(s) of " + product.getType() + " for " + product.getPrice() + ": " + (product.getPrice().multiply(new BigDecimal(product.getCount()))).setScale(0, RoundingMode.FLOOR).toString());
            }
            this.total = this.total.add(item.getAmount());
            System.out.println();
        }
        System.out.println(LocalDateTime.now().withNano(0) + " TOTAL: " + this.total.setScale(0, RoundingMode.FLOOR).toString());
        System.out.println(LocalDateTime.now().withNano(0) + " Supermarket is closed.");
    }
}
