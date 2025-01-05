package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceBase productService;
    private final ProductDomainMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllCity() {
        List<Product> productList = productService.findAll();
        List<ProductDTO> productListDTOList = productList.stream().map(productMapper::mapFromEntity).toList();

        return new ResponseEntity<>(productListDTOList, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") UUID productId) {
        Product product = productService.findById(productId);
        ProductDTO productDTO = productMapper.mapFromEntity(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody SaveProductDTO saveProductDTO) {
        Product product = productMapper.mapToEntity(saveProductDTO, null);
        Product newProduct = productService.save(product);
        ProductDTO newProductDTO = productMapper.mapFromEntity(newProduct);

        return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProductById(
            @PathVariable("productId") UUID productId,
            @RequestBody SaveProductDTO saveProductDTO
    ) {
        Product product = productMapper.mapToEntity(saveProductDTO);
        Product updateProduct = productService.updateById(productId, product);
        ProductDTO updateProductDTO = productMapper.mapFromEntity(updateProduct);

        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") UUID productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
