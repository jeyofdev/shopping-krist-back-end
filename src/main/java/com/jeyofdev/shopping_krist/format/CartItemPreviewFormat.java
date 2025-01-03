package com.jeyofdev.shopping_krist.format;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemPreviewFormat {
    private UUID id;
    private int quantity;
}
