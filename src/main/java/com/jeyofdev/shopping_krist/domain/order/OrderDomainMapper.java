package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemRepository;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
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
                getProfilePreviewResponse(order),
                getShippingAddressResponse(order),
                getCartItemListResponse(order)
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

    private ListRelationFormat<CartItemPreviewFormat> getCartItemListResponse(Order order) {
        return ListRelationFormat.get(order.getCartItems(), CartItemPreviewFormat::get);
    }

    private AddressDTO getShippingAddressResponse(Order order) {
        return order.getShippingAddress() != null ? new AddressDTO(
                order.getShippingAddress().getId(),
                order.getShippingAddress().getName(),
                order.getShippingAddress().getPhone(),
                order.getShippingAddress().getCity() != null ? AddressFormat.get(order.getShippingAddress()) : null
        ) : null;
    }

    private ProfilePreviewDTO getProfilePreviewResponse(Order order) {
        ProfilePreviewFormat profilePreviewFormat = ProfilePreviewFormat.builder()
                .id(order.getProfile().getId())
                .name(NameFormat.get(order.getProfile()))
                .phone(order.getProfile().getPhone())
                .address(order.getProfile().getAddress())
                .email(order.getProfile().getUser().getEmail())
                .build();

        return ProfilePreviewDTO.fromFormat(profilePreviewFormat);
    }
}
