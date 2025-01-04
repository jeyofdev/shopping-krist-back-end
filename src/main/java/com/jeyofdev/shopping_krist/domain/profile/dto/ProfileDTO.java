package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.dto.OrderDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.NameFormat;

import java.util.UUID;

public record ProfileDTO(
        UUID id,
        NameFormat name,
        String phone,
        String email,
        String address,
        ListRelationFormat<AddressDTO> deliveryAddressList,
        ProfileSettings profileSettings,
        ListRelationFormat<Notification> notificationList,
        ListRelationFormat<OrderDTO> orderList
) {
}
