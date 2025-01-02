package com.jeyofdev.shopping_krist.domain.profileSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileSettingsRepository extends JpaRepository<ProfileSettings, UUID> {
    @NonNull
    Optional<ProfileSettings> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);
}
