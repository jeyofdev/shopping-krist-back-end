package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.city.City;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService extends AbstractDomainServiceBase<Category, CategoryRepository> {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        super(categoryRepository, "category");
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateById(UUID categoryId, Category updatedCategory) {
        Category existingCategory = findById(categoryId);
        Category existingCategoryUpdated = Category.builder()
                .id(categoryId)
                .name(updatedCategory.getName() != null ? updatedCategory.getName() : existingCategory.getName())
                .build();

        return categoryRepository.save(existingCategoryUpdated);
    }

    @Transactional
    public void deleteById(UUID categoryId) {
        Category category = findById(categoryId);
        List<Product> productList = productRepository.findByCategory(category);

        for (Product product : productList) {
            product.getCategoryList().remove(category);
            productRepository.save(product);
        }

        categoryRepository.deleteById(categoryId);
    }
}
