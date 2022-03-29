package br.com.blz.testjava;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    private Product product;

    @BeforeEach
    public void setup(){
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("TESTE");
        warehouse.setType("PHYSICAL_STORE");
        warehouse.setQuantity(10);

        Inventory inventory = new Inventory();
        inventory.setWarehouses(Arrays.asList(warehouse));

        product = new Product();
        product.setInventory(inventory);
        product.setName("Produto Teste");
        product.setSku(1);

        service.create(product);
    }

    @DisplayName("Test Find by SKU success")
    @Test
    public void testFindBySku(){
        Product product = service.findBySku(1l).get();

        assertEquals(1l, product.getSku());
    }

    @DisplayName("Test create product success")
    @Test
    public void testCreate(){
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("TESTE");
        warehouse.setType("PHYSICAL_STORE");
        warehouse.setQuantity(10);

        Inventory inventory = new Inventory();
        inventory.setWarehouses(Arrays.asList(warehouse));

        Product product = new Product();
        product.setInventory(inventory);
        product.setName("Produto Teste");
        product.setSku(2);

        Long sku = service.create(product);

        assertEquals(2l, sku);
    }

    @DisplayName("Test update product success")
    @Test
    public void testUpdate(){
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("TESTE");
        warehouse.setType("PHYSICAL_STORE");
        warehouse.setQuantity(10);

        Inventory inventory = new Inventory();
        inventory.setWarehouses(Arrays.asList(warehouse));

        Product product = new Product();
        product.setInventory(inventory);
        product.setName("Produto Teste Update");
        product.setSku(2);

        service.update(product);
        Product productResult = service.findBySku(2l).get();

        assertEquals(product.getName(), productResult.getName());
    }

    @DisplayName("Test delete product success")
    @Test
    public void testDelete(){

        service.deleteBySku(2l);
        Optional<Product> productResult = service.findBySku(2l);

        assertTrue(!productResult.isPresent());
    }
}
