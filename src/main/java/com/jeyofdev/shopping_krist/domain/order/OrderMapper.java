package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.SaveOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDTO mapFromEntity(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCreatedAt(),
                order.getStatus(),
                order.getProfile(),
                order.getShippingAddress()
        );
    }

    public Order mapToEntity(SaveOrderDTO saveOrderDTO) {
        return Order.builder()
                .status(saveOrderDTO.status())
                .build();
    }
}
