package br.com.blz.testjava.exception;

public class ProductExistException extends RuntimeException {

    public ProductExistException(Long sku ) {
        super("Product sku: "+sku+" exist");
    }
}
