package com.example.LocalHunt.category.queryHandler;

import com.example.LocalHunt.Query;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryDTO;
import com.example.LocalHunt.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategoriesQueryHandler implements Query<Void, List<CategoryDTO>> {

    private final CategoryRepository categoryRepository;

    public GetCategoriesQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> execute(Void input) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories
                .stream()
                .map(CategoryDTO::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }
}
