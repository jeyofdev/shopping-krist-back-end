package com.jeyofdev.shopping_krist.domain.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findById(UUID id);

    void deleteById(UUID id);
}
