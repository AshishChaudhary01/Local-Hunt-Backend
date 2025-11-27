package com.example.LocalHunt.category.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryDTO;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryAlreadyExistsException;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateCategoryCommandHandler implements Command<UpdateCategoryCommand, CategoryDTO> {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public UpdateCategoryCommandHandler(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<CategoryDTO> execute(UpdateCategoryCommand command) {
        Category existingCategory = categoryRepository.findById(command.getId())
                .orElseThrow(CategoryNotFoundException::new);
        if (categoryRepository.existsByName(command.getRequest().getName())) {
            throw new CategoryAlreadyExistsException();
        }

        Category updatedCategory = new Category(command.getRequest());
        updatedCategory.setId(existingCategory.getId());

        categoryRepository.save(updatedCategory);

        return ResponseEntity.ok(new CategoryDTO(updatedCategory));
    }
}
