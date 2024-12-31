package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(@PathVariable("cartId") UUID cartId) throws NotFoundException {
        return cartRepository.findById(cartId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Cart with id {0} cannot be found", cartId)));
    }

    public Cart save(Cart cart) {
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());

        return cartRepository.save(cart);
    }

    public Cart updateById(UUID cartId, Cart updatedCart) {
        Cart existingCart = findById(cartId);
        Cart existingCartUpdated = Cart.builder()
                .id(cartId)
                .createdAt(existingCart.getCreatedAt())
                .updatedAt(new Date())
                .build();

        return cartRepository.save(existingCartUpdated);
    }

    @Transactional
    public void deleteById(UUID cartId) {
        findById(cartId);
        cartRepository.deleteById(cartId);
    }
}
