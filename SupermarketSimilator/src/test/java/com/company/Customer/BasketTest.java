package com.company.Customer;

import com.company.Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

public class BasketTest {
    private Product firstProduct = new Product("product#0", 1, 10, true);
    private Product secondProduct = new Product("product#1", 2, 20, true);
    private Product thirdProduct = new Product("product#2", 3, 30, false);

    @Test
    public void PickUpProduct() {
        Basket firstBasket = new Basket();
        Basket secondBasket = new Basket();
        firstBasket.PickUpProduct(this.firstProduct);
        secondBasket.PickUpProduct(this.firstProduct);
        Assert.assertEquals(firstBasket.getProducts(), secondBasket.getProducts());
    }

    @Test
    public void getProducts() {
        Basket firstBasket = new Basket();
        Basket secondBasket = new Basket();
        firstBasket.PickUpProduct(this.firstProduct);
        firstBasket.PickUpProduct(this.secondProduct);
        firstBasket.PickUpProduct(this.thirdProduct);
        secondBasket.PickUpProduct(this.firstProduct);
        secondBasket.PickUpProduct(this.secondProduct);
        secondBasket.PickUpProduct(this.thirdProduct);
        Assert.assertEquals(firstBasket.getProducts(), secondBasket.getProducts());
    }
}
