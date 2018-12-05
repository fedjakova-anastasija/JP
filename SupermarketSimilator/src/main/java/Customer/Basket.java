package Customer;
import Supermarket.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Product> productsList = new ArrayList<>();

    public List<Product> getProducts() {
        return this.productsList;
    }

    void PickUpProduct(Product product) {
        this.productsList.add(product);
    }
}
