package com.sgtslade.sites.base;

public class ProductDoesntExistException extends Exception {
    public ProductDoesntExistException(String msg){
        super(msg);
    }
}
