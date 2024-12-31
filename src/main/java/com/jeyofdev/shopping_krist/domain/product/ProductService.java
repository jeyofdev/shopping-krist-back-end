package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(@PathVariable("productId") UUID productId) throws NotFoundException {
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Product with id {0} cannot be found", productId)));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product updateById(UUID productId, Product updatedProduct) {
        Product existingProduct = findById(productId);
        Product existingProductUpdated = Product.builder()
                .id(productId)
                .brand(updatedProduct.getBrand() != null ? updatedProduct.getBrand() : existingProduct.getBrand())
                .name(updatedProduct.getName() != null ? updatedProduct.getName() : existingProduct.getName())
                .description(updatedProduct.getDescription() != null ? updatedProduct.getDescription() : existingProduct.getDescription())
                .price(updatedProduct.getPrice() != null ? updatedProduct.getPrice() : existingProduct.getPrice())
                .oldPrice(updatedProduct.getOldPrice() != null ? updatedProduct.getOldPrice() : existingProduct.getOldPrice())
                .stock(updatedProduct.getStock() != null ? updatedProduct.getStock() : existingProduct.getStock())
                .build();

        return productRepository.save(existingProductUpdated);
    }

    @Transactional
    public void deleteById(UUID productId) {
        findById(productId);
        productRepository.deleteById(productId);
    }
}
