package com.jeyofdev.shopping_krist.auth_user.dto;

import java.util.UUID;

public record AuthUserDTO(
        UUID id,
        String email,
        String role
) {
}
