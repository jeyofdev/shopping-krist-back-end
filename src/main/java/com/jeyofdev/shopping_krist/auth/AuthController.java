package com.jeyofdev.shopping_krist.auth;

import com.jeyofdev.shopping_krist.auth.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)  {
        RegisterResponse registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse authenticationResponse = authService.login(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/validate-account")
    public ResponseEntity<MessageResponse> validateAccount(@RequestParam("verificationToken") String verificationToken) {
        MessageResponse messageResponse = authService.validateAccount(verificationToken);

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
