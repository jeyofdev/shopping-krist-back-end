package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;
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
        private ColorEnum colorEnum;
        private SizeEnum sizeEnum;

        public static ProductPreviewFormat get(Product product) {
                return ProductPreviewFormat.builder()
                        .id(product.getId())
                        .brand(product.getBrand())
                        .name(product.getName())
                        .price(PriceFormat.get(product))
                        .colorEnum(product.getColor())
                        .sizeEnum(product.getSize())
                        .build();
        }
}
