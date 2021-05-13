package pl.ryspie.simpleshopapp.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public BigDecimal getOverallPrice(){
        return this.products.stream().
                map(Product::getProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
