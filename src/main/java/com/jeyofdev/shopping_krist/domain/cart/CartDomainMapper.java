package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
import com.jeyofdev.shopping_krist.format.NameFormat;
import com.jeyofdev.shopping_krist.format.ProfilePreviewFormat;
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
                ProfilePreviewFormat.builder()
                        .id(cart.getProfile().getId())
                        .name(NameFormat.builder()
                                .firstname(cart.getProfile().getFirstname())
                                .lastname(cart.getProfile().getLastname())
                                .build()
                        )
                        .phone(cart.getProfile().getPhone())
                        .address(cart.getProfile().getAddress())
                        .email(cart.getProfile().getUser().getEmail())
                        .build()
        );
    }

    @Override
    public Cart mapToEntity(SaveCartDTO saveCartDTO) {
        return Cart.builder()
                .build();
    }
}
