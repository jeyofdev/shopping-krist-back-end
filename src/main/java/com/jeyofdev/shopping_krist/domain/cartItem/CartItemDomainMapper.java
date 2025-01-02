package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.SaveCartItemDTO;
import org.springframework.stereotype.Component;

@Component
public class CartItemDomainMapper implements IDomainMapper<CartItem, CartItemDTO, SaveCartItemDTO> {
    @Override
    public CartItemDTO mapFromEntity(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getProduct(),
                cartItem.getCart()
        );
    }

    @Override
    public CartItem mapToEntity(SaveCartItemDTO saveCartItemDTO) {
        return CartItem.builder()
                .quantity(saveCartItemDTO.quantity())
                .build();
    }
}
