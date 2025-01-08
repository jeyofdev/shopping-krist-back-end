package com.jeyofdev.shopping_krist.auth;

import com.jeyofdev.shopping_krist.auth.model.*;
import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)  {
        RegisterResponse registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse authenticationResponse = authService.login(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/validate-account")
    @PermitAll
    public ResponseEntity<MessageResponse> validateAccount(@RequestParam("verificationToken") String verificationToken) {
        MessageResponse messageResponse = authService.validateAccount(verificationToken);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/update-password")
    @PermitAll
    public ResponseEntity<MessageResponse> updatePassword(
            @RequestBody UpdatePasswordRequest changePasswordRequest
    ) {
        MessageResponse messageResponse = authService.updatePassword(
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword()
        );

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    @PermitAll
    public ResponseEntity<MessageResponse> requestPasswordReset(@RequestParam("email") String email) {
        MessageResponse requestPasswordResetResponse = authService.requestPasswordReset(email);
        return new ResponseEntity<>(requestPasswordResetResponse, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    @PermitAll
    public ResponseEntity<MessageResponse> resetPassword(
            @RequestParam("resetToken") String resetToken,
            @RequestParam("newPassword") String newPassword
    ) {
        MessageResponse messageResponse = authService.resetPassword(resetToken, newPassword);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
