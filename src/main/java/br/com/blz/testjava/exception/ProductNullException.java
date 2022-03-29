package br.com.blz.testjava.exception;

public class ProductNullException extends RuntimeException {

    public ProductNullException() {
        super("Product is null");
    }
}
