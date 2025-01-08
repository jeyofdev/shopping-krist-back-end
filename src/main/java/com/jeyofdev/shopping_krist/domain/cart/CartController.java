package com.jeyofdev.shopping_krist.domain.cart;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.cart.dto.CartDTO;
import com.jeyofdev.shopping_krist.domain.cart.dto.SaveCartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<CartDTO>>> findAllCart() {
        List<Cart> cartList = cartService.findAll();
        List<CartDTO> cartDTOList = cartList.stream().map(cartMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cartDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<DomainSuccessResponse<CartDTO>> findCartById(@PathVariable("cartId") UUID cartId) {
        Cart cart = cartService.findById(cartId);
        CartDTO cartDTO = cartMapper.mapFromEntity(cart);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cartDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(ApiRoutes.PROFILE + "/{profileId}")
    public ResponseEntity<DomainSuccessResponse<CartDTO>> saveCart(
            @RequestBody SaveCartDTO saveCartDTO,
            @PathVariable("profileId") UUID profileId
    ) {
        Cart cart = cartMapper.mapToEntity(saveCartDTO);
        Cart newCart = cartService.save(cart, profileId);
        CartDTO newCartDTO = cartMapper.mapFromEntity(newCart);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newCartDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<DomainSuccessResponse<CartDTO>> updateCartById(
            @PathVariable("cartId") UUID cartId,
            @RequestBody SaveCartDTO saveCartDTO
    ) {
        Cart cart = cartMapper.mapToEntity(saveCartDTO);
        Cart updateCart = cartService.updateById(cartId, cart);
        CartDTO updateCartDTO = cartMapper.mapFromEntity(updateCart);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateCartDTO),
                HttpStatus.OK
        );
    }
}
