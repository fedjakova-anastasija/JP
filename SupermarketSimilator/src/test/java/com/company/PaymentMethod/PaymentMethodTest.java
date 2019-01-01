package com.company.PaymentMethod;

import com.company.Customer.Customer;
import org.junit.Assert;
import org.junit.Test;

public class PaymentMethodTest {
    private Customer firstCustomer = new Customer("adult", "cash");
    private Customer secondCustomer = new Customer("child", "card");
    private Customer thirdCustomer = new Customer("retired", "bonuses");
    private PaymentMethod cash = new PaymentMethod("cash");
    private PaymentMethod card = new PaymentMethod("card");
    private PaymentMethod bonuses = new PaymentMethod("bonuses");
    private PaymentMethod notExist = new PaymentMethod("wer");

    @Test
    public void getPaymentType() {
        Assert.assertEquals(this.firstCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "card");
        Assert.assertEquals(this.thirdCustomer.getPaymentMethod().toString().toLowerCase(), "bonuses");
        Assert.assertNotEquals(this.thirdCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertNotEquals(this.firstCustomer.getPaymentMethod().toString().toLowerCase(), "bonuses");
        Assert.assertNotEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertNotEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "qwe");
        Assert.assertEquals(this.cash.getPaymentType().toString().toLowerCase() , "cash");
        Assert.assertNotEquals(this.cash.getPaymentType().toString().toLowerCase() , "card");
        Assert.assertEquals(this.card.getPaymentType().toString().toLowerCase() , "card");
        Assert.assertNotEquals(this.card.getPaymentType().toString().toLowerCase() , "cash");
        Assert.assertEquals(this.bonuses.getPaymentType().toString().toLowerCase() , "bonuses");
        Assert.assertNotEquals(this.bonuses.getPaymentType().toString().toLowerCase() , "cash");
        Assert.assertEquals(this.notExist.getPaymentType().toString().toLowerCase() , "bonuses");
    }
}