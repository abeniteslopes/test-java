package br.com.blz.testjava.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    public long sku;
    public String name;
    public Inventory inventory;
    public boolean isMarketable;

    public boolean isMarketable() {
        return inventory.getQuantity() > 0;
    }
}
