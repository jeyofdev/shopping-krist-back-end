package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemRepository;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import com.jeyofdev.shopping_krist.format.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDomainMapper implements IDomainMapper<Order, OrderDTO, SaveOrderDTO> {
    private final CartItemRepository cartItemRepository;

    @Override
    public OrderDTO mapFromEntity(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCreatedAt(),
                order.getStatus(),
                ProfilePreviewFormat.builder()
                        .id(order.getProfile().getId())
                        .name(NameFormat.builder()
                                .firstname(order.getProfile().getFirstname())
                                .lastname(order.getProfile().getLastname())
                                .build()
                        )
                        .phone(order.getProfile().getPhone())
                        .address(order.getProfile().getAddress())
                        .email(order.getProfile().getUser().getEmail())
                        .build(),
                order.getShippingAddress() != null ? new AddressDTO(
                        order.getShippingAddress().getId(),
                        order.getShippingAddress().getName(),
                        order.getShippingAddress().getPhone(),
                        order.getShippingAddress().getCity() != null ? AddressFormat.builder()
                                .streetNumber(order.getShippingAddress().getStreetNumber())
                                .street(order.getShippingAddress().getStreet())
                                .zipCode(order.getShippingAddress().getZipCode())
                                .city(order.getShippingAddress().getCity().getName())
                                .build() : null
                ) : null,
                ListRelationFormat.<CartItemPreviewFormat>builder()
                        .size(order.getCartItems().size())
                        .results(order.getCartItems().stream()
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
                        .build()
        );
    }

    @Override
    public Order mapToEntity(SaveOrderDTO saveOrderDTO, Order existingOrder) {
        List<UUID> cartItemIds = saveOrderDTO.cartItemIds() != null && !saveOrderDTO.cartItemIds().isEmpty()
                ? saveOrderDTO.cartItemIds()
                : existingOrder.getCartItems().stream().map(CartItem::getId).toList();

        List<CartItem> cartItems = cartItemIds.stream()
                .map(cartItemId -> cartItemRepository.findById(cartItemId)
                        .orElseThrow(() -> new NotFoundException(MessageFormat.format("CartItem with id {0} not found", cartItemId))))
                .collect(Collectors.toList());


        return Order.builder()
                .status(saveOrderDTO.status())
                .cartItems(cartItems)
                .build();
    }
}
