package com.example.LocalHunt.category.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryDTO;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.category.CategoryRequest;
import com.example.LocalHunt.exceptions.CategoryAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddCategoryCommandHandler implements Command<CategoryRequest, CategoryDTO> {
    private final CategoryRepository categoryRepository;

    public AddCategoryCommandHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<CategoryDTO> execute(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryAlreadyExistsException();
        }
        Category newCategory = new Category(request);
        categoryRepository.save(newCategory);
        return ResponseEntity.ok(new CategoryDTO(newCategory));
    }
}