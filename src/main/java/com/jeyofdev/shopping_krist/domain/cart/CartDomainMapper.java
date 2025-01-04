package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.format.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartDomainMapper implements IDomainMapper<Cart, CartDTO, SaveCartDTO> {
    @Override
    public CartDTO mapFromEntity(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                ListRelationFormat.<CartItemPreviewFormat>builder()
                        .size(cart.getCartItemList().size())
                        .results(cart.getCartItemList().stream()
                                .map(cartItem -> CartItemPreviewFormat.builder()
                                        .id(cartItem.getId())
                                        .quantity(cartItem.getQuantity())
                                        .product(ProductPreviewFormat.builder()
                                                .id(cartItem.getProduct().getId())
                                                .brand(cartItem.getProduct().getBrand())
                                                .name(cartItem.getProduct().getName())
                                                .price(PriceFormat.builder()
                                                        .price(cartItem.getProduct().getPrice())
                                                        .oldPrice(cartItem.getProduct().getOldPrice())
                                                        .build())
                                                .color(cartItem.getProduct().getColor())
                                                .size(cartItem.getProduct().getSize())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                        .build(),
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
