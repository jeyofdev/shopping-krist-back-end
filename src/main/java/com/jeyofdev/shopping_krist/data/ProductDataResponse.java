package com.jeyofdev.shopping_krist.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataResponse {
    private String brand;
    private String name;
    private String description;
    private double price;
    private double oldPrice;
    private int stock;
    private String color;
    private String size;
}
