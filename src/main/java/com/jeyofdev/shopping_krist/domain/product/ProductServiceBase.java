package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductServiceBase extends AbstractDomainServiceBase<Product, ProductRepository> {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceBase(ProductRepository productRepository) {
        super(productRepository, "product");
        this.productRepository = productRepository;
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
                .color(updatedProduct.getColor() != null ? updatedProduct.getColor() : existingProduct.getColor())
                .size(updatedProduct.getSize() != null ? updatedProduct.getSize() : existingProduct.getSize())
                .build();

        return productRepository.save(existingProductUpdated);
    }

    @Transactional
    public void deleteById(UUID productId) {
        findById(productId);
        productRepository.deleteById(productId);
    }
}
