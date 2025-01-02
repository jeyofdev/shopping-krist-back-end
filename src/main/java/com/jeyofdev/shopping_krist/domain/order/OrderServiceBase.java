package com.jeyofdev.shopping_krist.domain.order;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.address.AddressRepository;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItemRepository;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceBase extends AbstractDomainServiceBase<Order, OrderRepository> {
    private final OrderRepository orderRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public OrderServiceBase(OrderRepository orderRepository, ProfileRepository profileRepository, AddressRepository addressRepository, CartItemRepository cartItemRepository) {
        super(orderRepository, "order");
        this.orderRepository = orderRepository;
        this.profileRepository = profileRepository;
        this.addressRepository = addressRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Order save(Order order, UUID profileId, UUID addressId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Profile with id {0} not found", profileId))
                );

        Address shippingAddress = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new NotFoundException(MessageFormat.format("Address with id {0} not found", addressId))
                );

        List<CartItem> cartItems = order.getCartItems().stream()
                .map(cartItem -> {
                    cartItem.setOrder(order);
                    return cartItem;
                })
                .collect(Collectors.toList());
        order.setCartItems(cartItems);

        order.setCreatedAt(new Date());
        order.setProfile(profile);
        order.setShippingAddress(shippingAddress);

        return orderRepository.save(order);

    }

    public Order updateById(UUID orderId, Order updatedOrder) {
        Order existingOrder = findById(orderId);
        existingOrder.setStatus(updatedOrder.getStatus() != null ? updatedOrder.getStatus() : existingOrder.getStatus());
        existingOrder.setCartItems(updatedOrder.getCartItems() != null ? updatedOrder.getCartItems() : existingOrder.getCartItems());

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void deleteById(UUID orderId) {
        findById(orderId);
        orderRepository.deleteById(orderId);
    }
}
