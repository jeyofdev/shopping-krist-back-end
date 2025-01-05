package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.format.NameFormat;
import com.jeyofdev.shopping_krist.format.ProfilePreviewFormat;

import java.util.UUID;

public record ProfilePreviewDTO(
        UUID id,
        NameFormat name,
        String phone,
        String address,
        String email
) {
    public static ProfilePreviewDTO fromFormat(ProfilePreviewFormat format) {
        return new ProfilePreviewDTO(
                format.getId(),
                format.getName(),
                format.getPhone(),
                format.getAddress(),
                format.getEmail()
        );
    }
}
