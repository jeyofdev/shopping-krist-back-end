package com.jeyofdev.shopping_krist.domain.address;

import com.jeyofdev.shopping_krist.domain.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(UUID id);

    void deleteById(UUID id);

    List<Address> findByCity(City city);
}
