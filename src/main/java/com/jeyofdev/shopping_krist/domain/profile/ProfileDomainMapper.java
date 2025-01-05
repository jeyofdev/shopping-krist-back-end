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

import java.util.function.Function;

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
                getDeliveryAddressListResponse(profile),
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

    private ListRelationFormat<AddressDTO> getDeliveryAddressListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getDeliveryAddressList(), deliveryAddress -> new AddressDTO(
                deliveryAddress.getId(),
                deliveryAddress.getName(),
                deliveryAddress.getPhone(),
                AddressFormat.get(deliveryAddress)
        ));
    }

    private ListRelationFormat<Notification> getNotificationListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getNotificationList(), Function.identity());
    }

    private ListRelationFormat<OrderDTO> getOrderListResponse(Profile profile) {
        return ListRelationFormat.get(profile.getOrderList(), order -> new OrderDTO(
                order.getId(),
                order.getCreatedAt(),
                order.getStatus(),
                getProfilPreviewResponse(order),
                getOrderAddressResponse(order),
                getOrderCartItemListResponse(order)
        ));
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
        return ListRelationFormat.get(order.getCartItems(), CartItemPreviewFormat::get);
    }
}
