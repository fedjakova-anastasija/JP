package Report;

import Supermarket.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Bill> billsList = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);

    public void setBillsList(Bill bill) {
        this.billsList.add(bill);
        //this.printBills();
    }

    /*private void printBills() {
        System.out.println("DOBAVILI BILLA");
        for (Bill item : this.billsList) {
            System.out.println(item.getAmount());
            for (Product product : item.getProductsList()) {
                System.out.println(product.getCount() + " " + product.getType() + " " + product.getPrice());
            }
        }
    }*/

    public void printReport() {
        System.out.println(LocalDateTime.now().withNano(0) + " REPORT:");
//        for (int i = 0; i < this.billsList.size(); ++i) {
//            System.out.println(LocalDateTime.now().withNano(0) + " SUM " + this.billsList.get(i).getAmount() + " of " + "BILL#" + i + ": ");
//            for (Product product : this.billsList.get(i).getProductsList()) {
//                System.out.println(LocalDateTime.now().withNano(0) + " " + product.getCount() + " unit(s) of " + product.getType() + " for " + product.getPrice() + ": " + (product.getPrice().multiply(new BigDecimal(product.getCount()))).setScale(0, RoundingMode.FLOOR).toString());
//            }
//            this.total = this.total.add(this.billsList.get(i).getAmount());
//            System.out.println();
//        }

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
