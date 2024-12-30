package com.jeyofdev.shopping_krist.domain.profile;

import com.jeyofdev.shopping_krist.domain.profile.dto.ProfileDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.SaveProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstname(),
                profile.getLastname(),
                profile.getPhone(),
                profile.getAddress()
        );
    }

    public Profile mapToEntity(SaveProfileDTO saveProfileDTO) {
        return Profile.builder()
                .firstname(saveProfileDTO.firstname())
                .lastname(saveProfileDTO.lastname())
                .phone(saveProfileDTO.phone())
                .address(saveProfileDTO.address())
                .build();
    }
}