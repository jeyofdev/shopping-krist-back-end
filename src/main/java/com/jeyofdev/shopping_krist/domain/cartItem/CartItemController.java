package com.jeyofdev.shopping_krist.domain.cartItem;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.SaveCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM)
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<CartItemDTO>>> findAllCartItem() {
        List<CartItem> cartItemList = cartItemService.findAll();
        List<CartItemDTO> cartItemDTOList = cartItemList.stream().map(cartItemMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cartItemDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<DomainSuccessResponse<CartItemDTO>> findCartItemById(@PathVariable("cartItemId") UUID cartItemId) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        CartItemDTO cartItemDTO = cartItemMapper.mapFromEntity(cartItem);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, cartItemDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(ApiRoutes.PRODUCT + "/{productId}" + ApiRoutes.CART + "/{cartId}")
    public ResponseEntity<DomainSuccessResponse<CartItemDTO>> saveCartItem(
            @RequestBody SaveCartItemDTO saveCartItemDTO,
            @PathVariable("productId") UUID productId,
            @PathVariable("cartId") UUID cartId
    ) {
        CartItem cartItem = cartItemMapper.mapToEntity(saveCartItemDTO);
        CartItem newCartItem = cartItemService.save(cartItem, productId, cartId);
        CartItemDTO newCartItemDTO = cartItemMapper.mapFromEntity(newCartItem);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newCartItemDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<DomainSuccessResponse<CartItemDTO>> updateCartItemById(
            @PathVariable("cartItemId") UUID cartItemId,
            @RequestBody SaveCartItemDTO saveCartItemDTO
    ) {
        CartItem cartItem = cartItemMapper.mapToEntity(saveCartItemDTO);
        CartItem updateCartItem = cartItemService.updateById(cartItemId, cartItem);
        CartItemDTO updateCartItemDTO = cartItemMapper.mapFromEntity(updateCartItem);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateCartItemDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteCartItemById(@PathVariable("cartItemId") UUID cartItemId) {
        String message = cartItemService.deleteById(cartItemId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
