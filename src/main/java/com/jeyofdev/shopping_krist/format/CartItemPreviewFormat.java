package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
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
    private ProductPreviewFormat product;

    public static CartItemPreviewFormat get(CartItem cartItem) {
        return CartItemPreviewFormat.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(ProductPreviewFormat.get(cartItem.getProduct()))
                .build();
    }

}
