package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.NameFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

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
                ListRelationFormat.<Address>builder()
                        .size(profile.getDeliveryAddressList().size())
                        .results(profile.getDeliveryAddressList())
                        .build(),
                profile.getProfileSettings(),
                ListRelationFormat.<Notification>builder()
                        .size(profile.getNotificationList().size())
                        .results(profile.getNotificationList())
                        .build(),
                ListRelationFormat.<Order>builder()
                        .size(profile.getOrderList().size())
                        .results(profile.getOrderList())
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
