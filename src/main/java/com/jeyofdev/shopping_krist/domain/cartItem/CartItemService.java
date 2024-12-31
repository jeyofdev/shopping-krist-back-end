package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    public CartItem findById(UUID cartItemId) throws NotFoundException {
        return cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity CartItem with id {0} cannot be found", cartItemId)));
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateById(UUID cartItemId, CartItem updatedCartItem) {
        CartItem existingCartItem = findById(cartItemId);
        CartItem existingCartItemUpdated = CartItem.builder()
                .id(cartItemId)
                .quantity(updatedCartItem.getQuantity() != null ? updatedCartItem.getQuantity() : existingCartItem.getQuantity())
                .build();

        return cartItemRepository.save(existingCartItemUpdated);
    }

    @Transactional
    public void deleteById(UUID cartItemId) {
        findById(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }
}
