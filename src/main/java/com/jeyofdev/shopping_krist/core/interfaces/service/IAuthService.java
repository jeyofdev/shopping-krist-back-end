package com.jeyofdev.shopping_krist.core.interfaces.service;

import com.jeyofdev.shopping_krist.auth.model.*;
import com.jeyofdev.shopping_krist.exception.BadValidationArgumentException;
import com.jeyofdev.shopping_krist.exception.ExpireTokenException;
import com.jeyofdev.shopping_krist.exception.InvalidTokenException;
import com.jeyofdev.shopping_krist.exception.UsernameAlreadyTakenException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthService {
    RegisterResponse register(RegisterRequest request) throws UsernameAlreadyTakenException;

    AuthResponse login(LoginRequest request)  throws BadCredentialsException, UsernameNotFoundException;

    MessageResponse validateAccount(String verificationToken) throws InvalidTokenException, ExpireTokenException;

    MessageResponse updatePassword(String oldPassword, String newPassword) throws IllegalStateException, BadValidationArgumentException, AccessDeniedException;

    MessageResponse requestPasswordReset(String email) throws UsernameNotFoundException;

    MessageResponse resetPassword(String token, String newPassword) throws IllegalStateException, ExpireTokenException, BadValidationArgumentException;
}
