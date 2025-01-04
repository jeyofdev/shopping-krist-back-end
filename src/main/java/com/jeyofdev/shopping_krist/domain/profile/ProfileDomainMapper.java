package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import com.jeyofdev.shopping_krist.format.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProfileDomainMapper implements IDomainMapper<Profile, ProfileDTO, SaveProfileDTO> {
    @Override
    public ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                NameFormat.builder()
                        .firstname(profile.getFirstname())
                        .lastname(profile.getLastname())
                        .build(),
                profile.getPhone(),
                profile.getUser().getEmail(),
                profile.getAddress(),
                ListRelationFormat.<AddressDTO>builder()
                        .size(profile.getDeliveryAddressList().size())
                        .results(profile.getDeliveryAddressList().stream()
                                .map(address -> new AddressDTO(
                                        address.getId(),
                                        address.getName(),
                                        address.getPhone(),
                                        AddressFormat.builder()
                                                .streetNumber(address.getStreetNumber())
                                                .street(address.getStreet())
                                                .zipCode(address.getZipCode())
                                                .city(address.getCity().getName())
                                                .build()
                                )).toList()
                        )

                        .build(),
                profile.getProfileSettings(),
                ListRelationFormat.<Notification>builder()
                        .size(profile.getNotificationList().size())
                        .results(profile.getNotificationList())
                        .build(),
                ListRelationFormat.<OrderDTO>builder()
                        .size(profile.getOrderList().size())
                        .results(profile.getOrderList().stream().map(order -> new OrderDTO(
                                        order.getId(),
                                        order.getCreatedAt(),
                                        order.getStatus(),
                                        ProfilePreviewFormat.builder()
                                                .id(order.getProfile().getId())
                                                .name(NameFormat.builder()
                                                        .firstname(order.getProfile().getFirstname())
                                                        .lastname(order.getProfile().getLastname())
                                                        .build())
                                                .phone(order.getProfile().getPhone())
                                                .address(order.getProfile().getAddress())
                                                .email(order.getProfile().getUser().getEmail())
                                                .build(),
                                        new AddressDTO(
                                                order.getShippingAddress().getId(),
                                                order.getShippingAddress().getName(),
                                                order.getShippingAddress().getPhone(),
                                                AddressFormat.builder()
                                                        .streetNumber(order.getShippingAddress().getStreetNumber())
                                                        .street(order.getShippingAddress().getStreet())
                                                        .zipCode(order.getShippingAddress().getZipCode())
                                                        .city(order.getShippingAddress().getCity().getName())
                                                        .build()
                                        ),
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
                                )).collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @Override
    public Profile mapToEntity(SaveProfileDTO saveProfileDTO) {
        return Profile.builder()
                .firstname(saveProfileDTO.firstname())
                .lastname(saveProfileDTO.lastname())
                .phone(saveProfileDTO.phone())
                .address(saveProfileDTO.address())
                .build();
    }
}
