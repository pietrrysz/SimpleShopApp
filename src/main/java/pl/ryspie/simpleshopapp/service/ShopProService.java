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
import java.math.RoundingMode;

@Service
@Profile("pro")
public class ShopProService implements Shop {
    private final Cart cart;
    @Value("${vat}")
    private double vat;
    @Value("${discount}")
    private double discount;


    @Autowired
    public ShopProService(Cart cart) {
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
                "Total price + vat: " + getTotalPriceWithVat() + "\n" +
                "Price (vat) after 15% discount : " + getTotalPriceWithVat().subtract(getTotalDiscount()) + "\n" +
                "Discount: " + getTotalDiscount());
        ;
    }

    private BigDecimal getTotalDiscount() {
        return (getTotalPriceWithVat().multiply(BigDecimal.valueOf(this.discount)).
                setScale(2, RoundingMode.HALF_UP));
    }

    private BigDecimal getTotalPriceWithVat() {
        return (cart.getOverallPrice().multiply(BigDecimal.valueOf(1 + this.vat))
        .setScale(2,RoundingMode.HALF_UP));
    }

}
