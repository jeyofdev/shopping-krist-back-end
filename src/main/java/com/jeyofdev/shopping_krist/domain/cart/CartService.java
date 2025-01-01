package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
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
    private final ProfileRepository profileRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(@PathVariable("cartId") UUID cartId) throws NotFoundException {
        return cartRepository.findById(cartId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Cart with id {0} cannot be found", cartId)));
    }

    public Cart save(Cart cart, UUID profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format("Entity Profile with id {0} cannot be found", profileId)));

        cart.setProfile(profile);
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());

        return cartRepository.save(cart);
    }

    public Cart updateById(UUID cartId, Cart updatedCart) {
        Cart existingCart = findById(cartId);

        existingCart.setUpdatedAt(new Date());
        existingCart.setCartItemList(updatedCart.getCartItemList());

        return cartRepository.save(existingCart);
    }
}