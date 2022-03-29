package br.com.blz.testjava;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    public void testFindBySku() throws Exception {
        this.mockMvc.perform(get("/product/1")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Produto Teste")));
    }

}
