package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.notification.dto.NotificationDTO;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.ProfileSettingsDTO;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.NameFormat;

import java.util.UUID;

public record ProfileDTO(
        UUID id,
        NameFormat name,
        String phone,
        String email,
        String address,
        ListRelationFormat<AddressDTO> deliveryAddresses,
        ProfileSettingsDTO profileSettings,
        ListRelationFormat<NotificationDTO> notifications,
        ListRelationFormat<OrderDTO> orders
) {
}
