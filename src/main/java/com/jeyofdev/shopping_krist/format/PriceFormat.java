package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.domain.product.Product;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceFormat {
    private double price;
    private double oldPrice;

    public static PriceFormat get(Product product) {
        return PriceFormat.builder()
                .price(product.getPrice())
                .oldPrice(product.getOldPrice())
                .build();
    }
}
