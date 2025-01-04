package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
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
        ListRelationFormat<Address> deliveryAddressList,
        ProfileSettings profileSettings,
        ListRelationFormat<Notification> notificationList,
        ListRelationFormat<Order> orderList
) {
}
