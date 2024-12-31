package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.SaveCartItemDTO;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemDTO mapFromEntity(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getQuantity()
        );
    }

    public CartItem mapToEntity(SaveCartItemDTO saveCartItemDTO) {
        return CartItem.builder()
                .quantity(saveCartItemDTO.quantity())
                .build();
    }
}
