package com.company.Customer;
import com.company.Supermarket.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket implements IBasket {
    private List<Product> productsList = new ArrayList<>();

    public List<Product> getProducts() {
        return this.productsList;
    }

    public void PickUpProduct(Product product) {
        this.productsList.add(product);
    }
}
