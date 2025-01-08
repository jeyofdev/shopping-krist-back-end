package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.domain.category.dto.SaveCategoryDTO;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @PermitAll
    public ResponseEntity<DomainSuccessResponse<List<CategoryDTO>>> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(categoryMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, categoryDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{categoryId}")
    @PermitAll
    public ResponseEntity<DomainSuccessResponse<CategoryDTO>> findCategoryById(@PathVariable("categoryId") UUID categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryDTO categoryDTO = categoryMapper.mapFromEntity(category);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, categoryDTO),
                HttpStatus.OK
        );
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DomainSuccessResponse<CategoryDTO>> saveCategory(
            @RequestBody SaveCategoryDTO saveCategoryDTO
    ) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category newCategory = categoryService.save(category);
        CategoryDTO newCategoryDTO = categoryMapper.mapFromEntity(newCategory);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newCategoryDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DomainSuccessResponse<CategoryDTO>> updateCategoryById(
            @PathVariable("categoryId") UUID categoryId,
            @RequestBody SaveCategoryDTO saveCategoryDTO
    ) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category updateCategory = categoryService.updateById(categoryId, category);
        CategoryDTO updateCategoryDTO = categoryMapper.mapFromEntity(updateCategory);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateCategoryDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteCategoryById(@PathVariable("categoryId") UUID categoryId) {
        String message = categoryService.deleteById(categoryId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
