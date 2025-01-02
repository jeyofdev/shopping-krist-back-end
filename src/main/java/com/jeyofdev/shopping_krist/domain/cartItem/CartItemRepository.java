package com.jeyofdev.shopping_krist.domain.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, UUID> {
    @NonNull
    Optional<CartItem> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);
}
