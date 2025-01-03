package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
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
}
