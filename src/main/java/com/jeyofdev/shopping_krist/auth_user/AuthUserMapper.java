package com.jeyofdev.shopping_krist.auth_user;

import com.jeyofdev.shopping_krist.auth_user.dto.AuthUserDTO;
import com.jeyofdev.shopping_krist.core.interfaces.mapper.IAuthUserMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper implements IAuthUserMapper<AuthUser, AuthUserDTO> {
    @Override
    public AuthUserDTO mapFromEntity(AuthUser authUser) {
        return new AuthUserDTO(
                authUser.getId(),
                authUser.getEmail(),
                authUser.getRole()
        );
    }
}
