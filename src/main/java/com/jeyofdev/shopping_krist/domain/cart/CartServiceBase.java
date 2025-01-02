package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class CartServiceBase extends AbstractDomainServiceBase<Cart, CartRepository> {
    private final CartRepository cartRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public CartServiceBase(CartRepository cartRepository, ProfileRepository profileRepository) {
        super(cartRepository, "cart");
        this.cartRepository = cartRepository;
        this.profileRepository = profileRepository;
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