package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
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

    private ListRelationFormat<CartItemPreviewFormat> getCartItemListResponse(Cart cart) {
        return ListRelationFormat.get(cart.getCartItemList(),CartItemPreviewFormat::get);
    }

    private ProfilePreviewDTO getProfilePreviewResponse(Cart cart) {
        ProfilePreviewFormat profilePreviewFormat = ProfilePreviewFormat.builder()
                .id(cart.getProfile().getId())
                .name(NameFormat.get(cart.getProfile()))
                .phone(cart.getProfile().getPhone())
                .address(cart.getProfile().getAddress())
                .email(cart.getProfile().getUser().getEmail())
                .build();

        return ProfilePreviewDTO.fromFormat(profilePreviewFormat);
    }
}
