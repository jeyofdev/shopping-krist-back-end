package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.domain.cart.CartRepository;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.ProductRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class CartItemServiceBase extends AbstractDomainServiceBase<CartItem, CartItemRepository> {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CartItemServiceBase(CartItemRepository cartItemRepository, ProductRepository productRepository, CartRepository cartRepository) {
        super(cartItemRepository, "cartItem");
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public CartItem save(CartItem cartItem, UUID productId, UUID cartId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Product with id {0} cannot be found", productId)));

        Cart cart =  cartRepository.findById(cartId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Cart with id {0} cannot be found", cartId)));

        cartItem.setProduct(product);
        cartItem.setCart(cart);
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateById(UUID cartItemId, CartItem updatedCartItem) {
        CartItem existingCartItem = findById(cartItemId);
        CartItem existingCartItemUpdated = CartItem.builder()
                .id(cartItemId)
                .quantity(updatedCartItem.getQuantity() != null ? updatedCartItem.getQuantity() : existingCartItem.getQuantity())
                .product(existingCartItem.getProduct())
                .cart(existingCartItem.getCart())
                .build();

        return cartItemRepository.save(existingCartItemUpdated);
    }

    @Transactional
    public void deleteById(UUID cartItemId) {
        findById(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }
}
