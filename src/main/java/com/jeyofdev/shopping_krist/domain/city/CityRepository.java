package com.jeyofdev.shopping_krist.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    @NonNull
    Optional<City> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);
}
