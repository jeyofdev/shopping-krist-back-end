package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID orderId) throws NotFoundException {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Order with id {0} cannot be found", orderId)));
    }

    public Order save(Order order) {
        order.setCreatedAt(new Date());
        return orderRepository.save(order);
    }

    public Order updateById(UUID orderId, Order updatedOrder) {
        Order existingOrder = findById(orderId);
        Order existingOrderUpdated = Order.builder()
                .id(orderId)
                .createdAt(existingOrder.getCreatedAt())
                .status(updatedOrder.getStatus() != null ? updatedOrder.getStatus() : existingOrder.getStatus())
                .build();

        return orderRepository.save(existingOrderUpdated);
    }

    @Transactional
    public void deleteById(UUID orderId) {
        findById(orderId);
        orderRepository.deleteById(orderId);
    }
}