package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
import org.springframework.stereotype.Component;

@Component
public class CartDomainMapper implements IDomainMapper<Cart, CartDTO, SaveCartDTO> {
    @Override
    public CartDTO mapFromEntity(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                cart.getCartItemList(),
                cart.getProfile()
        );
    }

    @Override
    public Cart mapToEntity(SaveCartDTO saveCartDTO) {
        return Cart.builder()
                .build();
    }
}
