package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.SaveCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart/item")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> findAllCartItem() {
        List<CartItem> cartItemList = cartItemService.findAll();
        List<CartItemDTO> cartItemDTOList = cartItemList.stream().map(cartItemMapper::mapFromEntity).toList();

        return new ResponseEntity<>(cartItemDTOList, HttpStatus.OK);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItemDTO> findCartItemById(@PathVariable("cartItemId") UUID cartItemId) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        CartItemDTO cartItemDTO = cartItemMapper.mapFromEntity(cartItem);

        return new ResponseEntity<>(cartItemDTO, HttpStatus.OK);
    }

    @PostMapping("/product/{productId}/cart/{cartId}")
    public ResponseEntity<CartItemDTO> saveCartItem(
            @RequestBody SaveCartItemDTO saveCartItemDTO,
            @PathVariable("productId") UUID productId,
            @PathVariable("cartId") UUID cartId
    ) {
        CartItem cartItem = cartItemMapper.mapToEntity(saveCartItemDTO);
        CartItem newCartItem = cartItemService.save(cartItem, productId, cartId);
        CartItemDTO newCartItemDTO = cartItemMapper.mapFromEntity(newCartItem);

        return new ResponseEntity<>(newCartItemDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemDTO> updateCartItemById(
            @PathVariable("cartItemId") UUID cartItemId,
            @RequestBody SaveCartItemDTO saveCartItemDTO
    ) {
        CartItem cartItem = cartItemMapper.mapToEntity(saveCartItemDTO);
        CartItem updateCartItem = cartItemService.updateById(cartItemId, cartItem);
        CartItemDTO updateCartItemDTO = cartItemMapper.mapFromEntity(updateCartItem);

        return new ResponseEntity<>(updateCartItemDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItemById(@PathVariable("cartItemId") UUID cartItemId) {
        cartItemService.deleteById(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
