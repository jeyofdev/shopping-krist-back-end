package com.jeyofdev.shopping_krist.domain.profileSettings.dto;

import com.jeyofdev.shopping_krist.core.enums.DarkModeEnum;

import java.util.UUID;

public record ProfileSettingsDTO(
        UUID id,
        DarkModeEnum appearance,
        boolean isPushNotification,
        boolean isEmailNotification
) {
}
