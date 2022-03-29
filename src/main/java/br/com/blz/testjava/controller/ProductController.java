package br.com.blz.testjava.controller;

import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ProductExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductNullException;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{sku}")
    public Product findBySku(@PathVariable("sku") Long sku) {
        try {
            Product product = service.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException(sku));

            return product;
        } catch (ProductNotFoundException exc) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product Not Found", exc);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Product resource) {
        try {
            Optional<Product> product = Optional.ofNullable(resource);
            product
            .orElseThrow(() -> new ProductNullException());

            if (service.findBySku(resource.getSku())
                .isPresent()){
                throw new ProductExistException(resource.getSku());
            }

            return service.create(resource);
        } catch (ProductNullException exc) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Product is null", exc);
        } catch (ProductExistException exc) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Product already exists", exc);
        }
    }

    @PutMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("sku") Long sku, @RequestBody Product resource) {
        try {

            Optional<Product> product = Optional.ofNullable(resource);
            product
                .orElseThrow(() -> new ProductNullException());

            service.findBySku(sku)
                .orElseThrow(() -> new ProductNotFoundException(sku));

            service.update(resource);
        } catch (ProductNullException exc) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Product is null", exc);
        } catch (ProductNotFoundException exc) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product Not Found", exc);

        }
    }

    @DeleteMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("sku") Long id) {
        service.deleteBySku(id);
    }

}
