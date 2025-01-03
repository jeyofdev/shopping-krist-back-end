package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import com.jeyofdev.shopping_krist.format.NameFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
                profile.getDeliveryAddressList() != null ? profile.getDeliveryAddressList() : new ArrayList<>(),
                profile.getProfileSettings(),
                profile.getNotificationList() != null ? profile.getNotificationList() : new ArrayList<>(),
                profile.getOrderList()
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
