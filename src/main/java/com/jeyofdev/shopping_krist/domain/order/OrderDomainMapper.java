package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemRepository;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
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
                order.getProfile(),
                order.getShippingAddress()
                /*order.getCartItems().stream()
                        .map(cartItem -> new CartItemDTO(cartItem.getId(), cartItem.getQuantity(), cartItem.getProduct(), cartItem.getCart()))
                        .collect(Collectors.toList())*/
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
