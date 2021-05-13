package pl.ryspie.simpleshopapp.interfaces;


public interface Shop {
    void addProductToCart(String productName);

    void showProductsWithStandardPrice();

    void showCartOverallPrice();

}
