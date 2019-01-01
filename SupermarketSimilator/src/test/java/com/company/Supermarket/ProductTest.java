package com.company.Supermarket;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
  private Product firstProduct = new Product("product#0", 1, 10, true);

  @Test
  public void getPrice() {
    Assert.assertEquals(this.firstProduct.getPrice().intValue(), 10);
  }

  @Test
  public void alcoholType() {
    Assert.assertEquals(this.firstProduct.alcoholType(), true);
  }
}