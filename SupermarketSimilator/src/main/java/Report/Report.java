package Report;

import Supermarket.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Bill> bill = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);

    public void setBill(Bill bill) {
        this.bill.add(bill);
    }

    public void printReport() {
        System.out.println(LocalDateTime.now().withNano(0) + " REPORT:");
        for (int i = 0; i < this.bill.size(); ++i) {
            System.out.println(LocalDateTime.now().withNano(0) + " SUM " + this.bill.get(i).getAmount() + " of " + "BILL#" + i + ": ");
            for (Product product : this.bill.get(i).getProductsList()) {
                System.out.println(LocalDateTime.now().withNano(0) + " " + product.getCount() + " unit(s) of " + product.getType() + " for " + product.getPrice() + ": " + (product.getPrice().multiply(new BigDecimal(product.getCount()))).setScale(0, RoundingMode.FLOOR).toString());
            }
            this.total = this.total.add(this.bill.get(i).getAmount());
            System.out.println();
        }
        System.out.println(LocalDateTime.now().withNano(0) + " TOTAL: " + this.total.setScale(0, RoundingMode.FLOOR).toString());
        System.out.println(LocalDateTime.now().withNano(0) + " Supermarket is closed.");
    }
}
