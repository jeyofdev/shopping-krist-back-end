package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.category.Category;
import com.jeyofdev.shopping_krist.domain.category.CategoryRepository;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService extends AbstractDomainServiceBase<Product, ProductRepository> {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProfileRepository profileRepository) {
        super(productRepository, "product");
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Product save(Product product) {
        if (product.getCategoryList() != null) {
            List<Category> categoryList = categoryRepository.findAllById(product.getCategoryList().stream().map(Category::getId).toList());
            product.setCategoryList(categoryList);
        }

        product.setCommentList(new ArrayList<>());
        product.setProfileList(new ArrayList<>());

        return productRepository.save(product);
    }

    @Transactional
    public Product addProductToProfile(UUID productId, UUID profileId) {
        Product product = findById(productId);
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format("Profile with id {0} not found", profileId))
        );

        product.getProfileList().add(profile);
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
}
