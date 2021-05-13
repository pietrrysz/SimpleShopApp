package pl.ryspie.simpleshopapp.model;


import org.springframework.beans.factory.annotation.Value;


import java.math.BigDecimal;
import java.util.Random;


public class Product {
    private final String productName;
    private final BigDecimal productPrice;


    public Product(String productName) {
        this.productName = productName;
        this.productPrice = ProductPrice.getRandomPrice();
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

   private static class ProductPrice {

        private static final int minProductPrice = 50;

        private static final int maxProductPrice = 300;

        private static BigDecimal getRandomPrice() {
            return BigDecimal.valueOf((new Random().nextInt((maxProductPrice - minProductPrice) + 1) + minProductPrice));
        }
    }
}
