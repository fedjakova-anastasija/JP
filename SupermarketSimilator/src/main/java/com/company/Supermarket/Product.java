package com.company.Supermarket;

import java.math.BigDecimal;

public class Product implements IProduct, Cloneable {
    private String type;
    private Integer count;
    private BigDecimal price = new BigDecimal(0);
    private Boolean alcoholType;

    public Product(String type, Integer count, Integer price, Boolean alcoholType) {
        setCount(count);
        setPrice(price);
        this.type = type;
        this.alcoholType = alcoholType;
    }

    public Integer getCount() {
        return this.count;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPrice(Integer price) {
        this.price = new BigDecimal(price);
    }

    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    public Boolean alcoholType() {
        return this.alcoholType;
    }
}
