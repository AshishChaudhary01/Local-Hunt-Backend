package com.example.LocalHunt.category.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryDeletionConflictException;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import com.example.LocalHunt.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryCommandHandler implements Command<String, Void> {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DeleteCategoryCommandHandler(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }
        if (productRepository.existsProductByCategory_Id(id)) {
            throw new CategoryDeletionConflictException();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
