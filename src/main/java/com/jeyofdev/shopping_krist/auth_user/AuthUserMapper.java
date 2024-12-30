package com.jeyofdev.shopping_krist.auth_user;

import com.jeyofdev.shopping_krist.auth_user.dto.AuthUserDTO;

public class AuthUserMapper {
    public static AuthUserDTO mapFromEntity(AuthUser authUser) {
        return new AuthUserDTO(
                authUser.getId(),
                authUser.getUsername()
        );
    }
}
