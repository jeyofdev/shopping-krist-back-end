package com.jeyofdev.shopping_krist.auth_user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public List<AuthUser> findAll() throws AccessDeniedException {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if(roles.equals("[ROLE_ADMIN]")) {
            return authUserRepository.findAll();
        } else {
            throw new AccessDeniedException("User does not have the correct rights to access to this resource");
        }
    }

    public AuthUser findUserByEmail(String email) throws AccessDeniedException {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (username.equals(email) || roles.equals("[ROLE_ADMIN]")) {
            return authUserRepository.findByEmail(email).orElse(null);
        } else {
            throw new AccessDeniedException("User does not have the correct rights to access to this resource");
        }
    }

    public AuthUser findUserById(UUID userId) throws AccessDeniedException {
        UUID id = ((AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (id.equals(userId) || roles.equals("[ROLE_ADMIN]")) {
            return authUserRepository.findById(userId).orElse(null);
        } else {
            throw new AccessDeniedException("User does not have the correct rights to access to this resource");
        }
    }
}
