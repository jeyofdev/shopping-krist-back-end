package com.jeyofdev.shopping_krist.auth_user;

import com.jeyofdev.shopping_krist.auth_user.dto.AuthUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService authUserService;

    @GetMapping
    public ResponseEntity<List<AuthUserDTO>> getAllUsers()  {
        List<AuthUser> authUserList = authUserService.findAll();
        List<AuthUserDTO> authUserDTOList = authUserList.stream()
                .map(AuthUserMapper::mapFromEntity)
                .toList();

        return new ResponseEntity<>(authUserDTOList, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthUserDTO> getUserByEmail(@PathVariable("email") String email, HttpServletRequest request) {
        AuthUser authUser = authUserService.findUserByEmail(email);
        AuthUserDTO authUserDTO = AuthUserMapper.mapFromEntity(authUser);

        return new ResponseEntity<>(authUserDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AuthUserDTO> getUserById(@PathVariable("userId") UUID userId) {
        AuthUser authUser = authUserService.findUserById(userId);
        AuthUserDTO authUserDTO = AuthUserMapper.mapFromEntity(authUser);
        return new ResponseEntity<>(authUserDTO, HttpStatus.OK);
    }
}
