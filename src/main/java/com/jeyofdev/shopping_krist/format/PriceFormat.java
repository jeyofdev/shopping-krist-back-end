package com.jeyofdev.shopping_krist.format;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceFormat {
    private double price;
    private double oldPrice;
}
