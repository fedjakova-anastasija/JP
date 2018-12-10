package Customer;

import Supermarket.Product;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    private Product firstProduct = new Product("product#0", 1, 10, true);
    private Customer firstCustomer = new Customer("adult", "cash");
    private Customer secondCustomer = new Customer("child", "card");
    private Customer thirdCustomer = new Customer("retired", "bonuses");

    @Test
    public void getPaymentMethod() {
        Assert.assertEquals(this.firstCustomer.getPaymentMethod().toString().toLowerCase(), "cash");
        Assert.assertEquals(this.secondCustomer.getPaymentMethod().toString().toLowerCase(), "card");
        Assert.assertEquals(this.thirdCustomer.getPaymentMethod().toString().toLowerCase(), "bonuses");
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
        Assert.assertEquals((long)this.firstCustomer.getBasket().getProducts().size(), 1L);
    }
}