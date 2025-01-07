package com.jeyofdev.shopping_krist.domain.profileSettings.dto;

import com.jeyofdev.shopping_krist.core.enums.DarkModeEnum;

public record SaveProfileSettingsDTO(
        DarkModeEnum appearance,

        boolean isPushNotification,
        boolean isEmailNotification
) {
}
