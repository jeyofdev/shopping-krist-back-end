package com.jeyofdev.shopping_krist.domain.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @NonNull
    Optional<Notification> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);
}
