package com.jeyofdev.shopping_krist.core.interfaces;

import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.UUID;

public interface IAuthUserService {
    List<AuthUser> findAll() throws AccessDeniedException;

    AuthUser findUserByEmail(String email);

    AuthUser findUserById(UUID userId);
}
