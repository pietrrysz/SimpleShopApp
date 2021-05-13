package pl.ryspie.simpleshopapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.ryspie.simpleshopapp.interfaces.Shop;
import pl.ryspie.simpleshopapp.model.Cart;
import pl.ryspie.simpleshopapp.model.Product;

import java.math.BigDecimal;

@Service
@Profile("plus")
public class ShopPlusService implements Shop {
    private final Cart cart;
    @Value("${vat}")
    private double vat;


    @Autowired
    public ShopPlusService(Cart cart) {
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
                .forEach(product -> System.out.println(product.getProductName() +
                        " - " +
                        product.getProductPrice()));
        ;

    }

    @Override
    public void showCartOverallPrice() {
        System.out.println("Total price: " + cart.getOverallPrice() + "\n" +
                "Vat: " + (getTotalPriceWithVat())
                .subtract(cart.getOverallPrice()) + "\n" +
                "Total price + vat: " + getTotalPriceWithVat());

    }

    private BigDecimal getTotalPriceWithVat() {
        return cart.getOverallPrice().multiply(BigDecimal.valueOf(1 + this.vat));
    }
}
