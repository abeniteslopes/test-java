package br.com.blz.testjava.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Inventory{
    public int quantity;
    public List<Warehouse> warehouses;

    public int getQuantity(){
        return warehouses.stream()
            .mapToInt(x -> x.getQuantity())
            .sum();
    }
}
