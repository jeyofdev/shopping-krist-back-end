package com.jeyofdev.shopping_krist.domain.profileSettings.dto;

import com.jeyofdev.shopping_krist.core.enums.DarkMode;

public record SaveProfileSettingsDTO(
        DarkMode appearance,

        boolean isPushNotification,
        boolean isEmailNotification
) {
}
