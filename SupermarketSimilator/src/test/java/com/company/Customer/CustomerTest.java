package com.company.Customer;

import com.company.Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    private Product firstProduct = new Product("product#0", 1, 10, true);
    private Product secondProduct = new Product("product#1", 1, 20, true);
    private Customer firstCustomer = new Customer("adult", "cash");
    private Customer secondCustomer = new Customer("child", "card");
    private Customer thirdCustomer = new Customer("retired", "bonuses");

    @Test
    public void getPaymentMethod() {
        Assert.assertEquals(this.firstCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "card");
        Assert.assertEquals(this.thirdCustomer.getPaymentMethod().toString().toLowerCase(), "bonuses");
        Assert.assertNotEquals(this.thirdCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertNotEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "bonuses");
        Assert.assertNotEquals(this.firstCustomer.getPaymentMethod().toString().toLowerCase(), "card");
        Assert.assertEquals(this.firstCustomer.getType().toString().toLowerCase(), "adult");
        Assert.assertEquals(this.secondCustomer.getType().toString().toLowerCase(), "child");
        Assert.assertEquals(this.thirdCustomer.getType().toString().toLowerCase(), "retired");
        Assert.assertNotEquals(this.secondCustomer.getType().toString().toLowerCase(), "adult");
        Assert.assertNotEquals(this.thirdCustomer.getType().toString().toLowerCase(), "child");
        Assert.assertNotEquals(this.firstCustomer.getType().toString().toLowerCase(), "retired");
        Customer fourthCustomer = new Customer("lol", "bonuses");
        Assert.assertEquals(fourthCustomer.getType() , null);
    }

    @Test
    public void getType() {
        Assert.assertEquals(this.firstCustomer.getType().toString().toLowerCase(), "adult");
    }

    @Test
    public void getBasket() throws CloneNotSupportedException {
        this.firstCustomer.ChooseProduct(this.firstProduct);
        Assert.assertEquals((long)this.firstCustomer.getBasket().getProducts().size(), 1L);
    }

    @Test
    public void ChooseProduct() throws CloneNotSupportedException {
        this.firstCustomer.ChooseProduct(this.firstProduct);
        this.firstCustomer.ChooseProduct(this.firstProduct);
        this.secondCustomer.ChooseProduct(this.firstProduct);
        this.secondCustomer.ChooseProduct(this.secondProduct);
        Assert.assertEquals((long)this.firstCustomer.getBasket().getProducts().size(), 1L);
        Assert.assertEquals((long)this.secondCustomer.getBasket().getProducts().size(), 2);
        Assert.assertEquals((long)this.thirdCustomer.getBasket().getProducts().size(), 0);
    }
}