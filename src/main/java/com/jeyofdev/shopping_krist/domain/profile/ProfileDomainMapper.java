package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import com.jeyofdev.shopping_krist.format.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfileDomainMapper implements IDomainMapper<Profile, ProfileDTO, SaveProfileDTO> {

    @Override
    public ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                NameFormat.get(profile),
                profile.getPhone(),
                profile.getUser().getEmail(),
                profile.getAddress(),
                getAddressListResponse(profile),
                profile.getProfileSettings(),
                getNotificationListResponse(profile),
                getOrderListResponse(profile)
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

    private ListRelationFormat<AddressDTO> getAddressListResponse(Profile profile) {
        return ListRelationFormat.<AddressDTO>builder()
                .size(profile.getDeliveryAddressList().size())
                .results(profile.getDeliveryAddressList().stream()
                        .map(address -> new AddressDTO(
                                address.getId(),
                                address.getName(),
                                address.getPhone(),
                                AddressFormat.get(address)
                        )).toList()
                )

                .build();
    }

    private ListRelationFormat<Notification> getNotificationListResponse(Profile profile) {
        return ListRelationFormat.<Notification>builder()
                .size(profile.getNotificationList().size())
                .results(profile.getNotificationList())
                .build();
    }

    private ListRelationFormat<OrderDTO> getOrderListResponse(Profile profile) {
        return ListRelationFormat.<OrderDTO>builder()
                .size(profile.getOrderList().size())
                .results(profile.getOrderList().stream().map(order -> new OrderDTO(
                                order.getId(),
                                order.getCreatedAt(),
                                order.getStatus(),
                                getProfilPreviewResponse(order),
                                getOrderAddressResponse(order),
                                getOrderCartItemListResponse(order)
                        )).collect(Collectors.toList())
                )
                .build();
    }

    private ProfilePreviewDTO getProfilPreviewResponse(Order order) {
        ProfilePreviewFormat profilePreviewFormat = ProfilePreviewFormat.get(order.getProfile());
        return ProfilePreviewDTO.fromFormat(profilePreviewFormat);
    }

    private AddressDTO getOrderAddressResponse(Order order) {
        return new AddressDTO(
                order.getShippingAddress().getId(),
                order.getShippingAddress().getName(),
                order.getShippingAddress().getPhone(),
                AddressFormat.get(order.getShippingAddress())
        );
    }

    private ListRelationFormat<CartItemPreviewFormat> getOrderCartItemListResponse(Order order) {
        return ListRelationFormat.<CartItemPreviewFormat>builder()
                .size(order.getCartItems().size())
                .results(order.getCartItems().stream()
                        .map(CartItemPreviewFormat::get)
                        .collect(Collectors.toList()))
                .build();
    }
}
