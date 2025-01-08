package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<DomainSuccessResponse<List<ProductDTO>>> findAllCity() {
        List<Product> productList = productService.findAll();
        List<ProductDTO> productListDTOList = productList.stream().map(productMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, productListDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<DomainSuccessResponse<ProductDTO>> findProductById(@PathVariable("productId") UUID productId) {
        Product product = productService.findById(productId);
        ProductDTO productDTO = productMapper.mapFromEntity(product);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, productDTO),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<DomainSuccessResponse<ProductDTO>> saveProduct(@RequestBody SaveProductDTO saveProductDTO) {
        Product product = productMapper.mapToEntity(saveProductDTO, null);
        Product newProduct = productService.save(product);
        ProductDTO newProductDTO = productMapper.mapFromEntity(newProduct);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newProductDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("/{productId}" + ApiRoutes.PROFILE + "/{profileId}")
    public ResponseEntity<DomainSuccessResponse<ProductDTO>> addProductToWishlish(
            @PathVariable("productId") UUID productId,
            @PathVariable("profileId") UUID profileId
    ) {
        Product product = productService.addProductToProfile(productId, profileId);
        ProductDTO productDTO = productMapper.mapFromEntity(product);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, productDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<DomainSuccessResponse<ProductDTO>> updateProductById(
            @PathVariable("productId") UUID productId,
            @RequestBody SaveProductDTO saveProductDTO
    ) {
        Product product = productMapper.mapToEntity(saveProductDTO, null);
        Product updateProduct = productService.updateById(productId, product);
        ProductDTO updateProductDTO = productMapper.mapFromEntity(updateProduct);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateProductDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteProductById(@PathVariable("productId") UUID productId) {
        String message = productService.deleteById(productId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
