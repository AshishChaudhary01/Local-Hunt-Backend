package com.example.LocalHunt.category.queryHandler;

import com.example.LocalHunt.Query;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryDTO;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetCategoryByIdQueryHandler implements Query<String, CategoryDTO> {
    private final CategoryRepository categoryRepository;

    public GetCategoryByIdQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ResponseEntity<CategoryDTO> execute(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        return ResponseEntity.ok(new CategoryDTO(category));
    }
}
