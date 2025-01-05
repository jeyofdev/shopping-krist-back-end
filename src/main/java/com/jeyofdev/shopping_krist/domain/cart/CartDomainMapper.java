package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemPreviewDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
import com.jeyofdev.shopping_krist.format.*;
import org.springframework.stereotype.Component;

@Component
public class CartDomainMapper implements IDomainMapper<Cart, CartDTO, SaveCartDTO> {
    @Override
    public CartDTO mapFromEntity(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                getCartItemListResponse(cart),
                getProfilePreviewResponse(cart)
        );
    }

    @Override
    public Cart mapToEntity(SaveCartDTO saveCartDTO) {
        return Cart.builder()
                .build();
    }

    private ListRelationFormat<CartItemPreviewDTO> getCartItemListResponse(Cart cart) {
        return ListRelationFormat.get(cart.getCartItemList(), cartItem -> new CartItemPreviewDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                ProductPreviewFormat.get(cartItem.getProduct())
        ));
    }

    private ProfilePreviewDTO getProfilePreviewResponse(Cart cart) {
        ProfilePreviewFormat profilePreviewFormat = ProfilePreviewFormat.get(cart.getProfile());
        return ProfilePreviewDTO.fromFormat(profilePreviewFormat);
    }
}
