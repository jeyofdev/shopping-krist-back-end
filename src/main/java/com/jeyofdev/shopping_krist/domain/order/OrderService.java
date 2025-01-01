package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
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
    private final ProfileRepository profileRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID orderId) throws NotFoundException {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Order with id {0} cannot be found", orderId)));
    }

    public Order save(Order order, UUID profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Profile with id {0} not found", profileId))
                );

        order.setCreatedAt(new Date());
        order.setProfile(profile);

        return orderRepository.save(order);

    }

    public Order updateById(UUID orderId, Order updatedOrder) {
        Order existingOrder = findById(orderId);
        existingOrder.setStatus(updatedOrder.getStatus() != null ? updatedOrder.getStatus() : existingOrder.getStatus());

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void deleteById(UUID orderId) {
        findById(orderId);
        orderRepository.deleteById(orderId);
    }
}
