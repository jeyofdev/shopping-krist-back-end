package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.product.Product;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPreviewFormat {
        private UUID id;
        private String brand;
        private String name;
        private PriceFormat price;
        private Color color;
        private Size size;

        public static ProductPreviewFormat get(Product product) {
                return ProductPreviewFormat.builder()
                        .id(product.getId())
                        .brand(product.getBrand())
                        .name(product.getName())
                        .price(PriceFormat.get(product))
                        .color(product.getColor())
                        .size(product.getSize())
                        .build();
        }
}
