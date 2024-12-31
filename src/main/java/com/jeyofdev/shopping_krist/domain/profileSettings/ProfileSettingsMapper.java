package com.jeyofdev.shopping_krist.domain.profileSettings;

import com.jeyofdev.shopping_krist.domain.profileSettings.dto.ProfileSettingsDTO;
import com.jeyofdev.shopping_krist.domain.profileSettings.dto.SaveProfileSettingsDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfileSettingsMapper {
    public ProfileSettingsDTO mapFromEntity(ProfileSettings profileSettings) {
        return new ProfileSettingsDTO(
                profileSettings.getId(),
                profileSettings.getAppearance(),
                profileSettings.isPushNotification(),
                profileSettings.isEmailNotification()
        );
    }

    public ProfileSettings mapToEntity(SaveProfileSettingsDTO saveProfileSettingsDTO) {
        return ProfileSettings.builder()
                .appearance(saveProfileSettingsDTO.appearance())
                .isPushNotification(saveProfileSettingsDTO.isPushNotification())
                .isEmailNotification(saveProfileSettingsDTO.isEmailNotification())
                .build();
    }
}
