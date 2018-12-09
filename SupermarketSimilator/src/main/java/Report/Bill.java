package Report;

import Supermarket.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private BigDecimal amount = new BigDecimal(0);
    private List<Product> productsList;

    public Bill(BigDecimal amount, List<Product> productsList) {
        setAmount(amount);
        setProductsList(productsList);
    }

    public List<Product> getProductsList() {
        return this.productsList;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    private void setProductsList(List<Product> productsList) {
        this.productsList = new ArrayList<>();
        this.productsList.clear();
        this.productsList = productsList;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
