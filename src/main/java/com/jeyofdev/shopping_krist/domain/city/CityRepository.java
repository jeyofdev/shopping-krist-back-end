package com.jeyofdev.shopping_krist.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(UUID id);

    void deleteById(UUID id);
}
