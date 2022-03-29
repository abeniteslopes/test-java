package br.com.blz.testjava.service;

import br.com.blz.testjava.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private Map<Long,Product> productsMap = new HashMap<>();

    public Optional<Product> findBySku(Long sku){

        return Optional.ofNullable(productsMap.get(sku));
    }

    public Long create(Product resource){
        productsMap.put(resource.getSku(), resource);
        return resource.getSku();
    }

    public void update(Product resource){
        productsMap.put(resource.getSku(), resource);
    }

    public Long deleteBySku(Long sku){
        productsMap.remove(sku);
        return sku;
    }
}
