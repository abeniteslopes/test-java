package br.com.blz.testjava.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long sku) {
        super("Could not find product " + sku);
    }
}
