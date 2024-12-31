package com.jeyofdev.shopping_krist.domain.profileSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileSettingsRepository extends JpaRepository<ProfileSettings, Long> {
    Optional<ProfileSettings> findById(UUID id);

    void deleteById(UUID id);
}
