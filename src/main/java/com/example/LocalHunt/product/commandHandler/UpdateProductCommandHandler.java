package com.example.LocalHunt.product.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import com.example.LocalHunt.exceptions.ProductNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductDTO;
import com.example.LocalHunt.product.ProductRepository;
import com.example.LocalHunt.product.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public UpdateProductCommandHandler(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {

        Product existingProduct = productRepository.findById(command.getId())
                .orElseThrow(ProductNotFoundException::new);
        Category updatedToCategory = (Category) categoryRepository.findByName(command.getRequest().getCategory())
                .orElseThrow(CategoryNotFoundException::new);

        Product updatedProduct = new Product(command.getRequest(), updatedToCategory);
        updatedProduct.setId(existingProduct.getId());
        updatedProduct.setAverageRating(existingProduct.getAverageRating());
        updatedProduct.setTotalReviews(existingProduct.getTotalReviews());
        updatedProduct.setCreatedAt(existingProduct.getCreatedAt());

        productRepository.save(updatedProduct);
        return ResponseEntity.ok(new ProductDTO(updatedProduct));
    }
}
