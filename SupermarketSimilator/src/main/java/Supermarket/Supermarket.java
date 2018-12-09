package Supermarket;

import Customer.Customer;
import Report.Report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private List<Customer> customersList = new ArrayList<>();
    private List<Product> productsList = new ArrayList<>();
    private CashDesk cashDesk = new CashDesk();
    private Report report = new Report();

    public Customer getCustomer(Integer id) {
        return this.customersList.get(id);
    }

    public Integer getCustomersCount() {
        return this.customersList.size();
    }

    public Integer getProductsCount() {
        return this.productsList.size();
    }

    public Product getProduct(Integer id) {
        return this.productsList.get(id);
    }

    public CashDesk getCashDesk() {
        return this.cashDesk;
    }

    public Report getReport() {
        return this.report;
    }

    public void addProduct(String productType, Integer count, Integer productPrice, boolean alcoholType) {
        Product product = new Product(productType, count, productPrice, alcoholType);
        this.productsList.add(product);
    }

    public void addCustomer(String productType, String paymentMethod) {
        Customer customer = new Customer(productType, paymentMethod);
        this.customersList.add(customer);
        System.out.println(LocalDateTime.now().withNano(0) + " New customer " + customer.getType().toString().toLowerCase() + " 'customer#" + this.customersList.indexOf(customer) + "' arrived");
    }

    public void changeProductCount(int count, int id) {
        Product product = this.productsList.get(id);
        product.setCount(count);
        this.productsList.set(id, product);
    }

    public void ToCashDesk(Integer customerIndex) {
        this.report.setBillsList(this.cashDesk.getBill(getCustomer(customerIndex)));
        this.customersList.set(customerIndex, null);
    }
}
