package pl.ryspie.simpleshopapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.ryspie.simpleshopapp.interfaces.Shop;
import pl.ryspie.simpleshopapp.model.Cart;
import pl.ryspie.simpleshopapp.model.Product;

@Service
@Profile("standard")
public class ShopStandardService implements Shop {

    private final Cart cart;

    @Autowired
    public ShopStandardService(Cart cart) {
        this.cart = cart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createCartWithFiveProducts() {
        addProductToCart("Ksiazka");
        addProductToCart("PLecak");
        addProductToCart("Myszka");
        addProductToCart("Piecyk");
        addProductToCart("Walizka");
        showProductsWithStandardPrice();
        showCartOverallPrice();
    }


    @Override
    public void addProductToCart(String productName) {
        cart.addProduct(new Product(productName));
    }

    @Override
    public void showProductsWithStandardPrice() {
        cart.getProducts()
                .forEach(product -> System.out.println(
                        new StringBuilder(product.getProductName())
                                .append(" - ").
                                append(product.getProductPrice())));

    }

    @Override
    public void showCartOverallPrice() {
        System.out.println(cart.getOverallPrice());
    }
}
