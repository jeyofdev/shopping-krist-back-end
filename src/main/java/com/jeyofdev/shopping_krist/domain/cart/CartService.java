package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class CartService extends AbstractDomainServiceBase<Cart, CartRepository> {
    private final CartRepository cartRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProfileRepository profileRepository) {
        super(cartRepository, "cart");
        this.cartRepository = cartRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Cart save(Cart cart, UUID profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format("Entity Profile with id {0} cannot be found", profileId)));

        cart.setProfile(profile);
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());
        cart.setCartItemList(new ArrayList<>());

        return cartRepository.save(cart);
    }

    public Cart updateById(UUID cartId, Cart updatedCart) {
        Cart existingCart = findById(cartId);

        existingCart.setUpdatedAt(new Date());

        return cartRepository.save(existingCart);
    }
}