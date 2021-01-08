package com.sgtslade.sites.base;

public interface Automated {
    String getProductUrl(String product) throws ProductDoesntExistException;
    boolean productAvailable(String product);
    void addToCart();
    void setAutofiller();
    void finalizePurchase();
    void buyProduct(String product);
}
