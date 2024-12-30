package com.jeyofdev.shopping_krist.auth_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);

    Optional<AuthUser> findById(UUID id);

    Optional<AuthUser> findByVerificationToken(String verificationToken);

    Optional<AuthUser> findByResetToken(String resetToken);
}
