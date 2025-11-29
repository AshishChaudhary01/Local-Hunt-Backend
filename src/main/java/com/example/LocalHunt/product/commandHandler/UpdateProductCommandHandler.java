package com.example.LocalHunt.product.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import com.example.LocalHunt.exceptions.ProductNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductDTO;
import com.example.LocalHunt.product.ProductRepository;
import com.example.LocalHunt.product.productVariant.ProductVariant;
import com.example.LocalHunt.product.productVariant.ProductVariantRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        //Update product details from request
        existingProduct.updateFromRequest(command.getRequest(), updatedToCategory);

        productRepository.save(existingProduct);
        return ResponseEntity.ok(new ProductDTO(existingProduct));
    }
}
