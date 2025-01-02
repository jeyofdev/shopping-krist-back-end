package com.jeyofdev.shopping_krist.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @NonNull
    Optional<Order> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);
}
