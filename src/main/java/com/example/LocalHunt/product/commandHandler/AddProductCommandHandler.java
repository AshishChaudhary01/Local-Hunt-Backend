package com.example.LocalHunt.product.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.category.CategoryRepository;
import com.example.LocalHunt.exceptions.CategoryNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductDTO;
import com.example.LocalHunt.product.ProductRepository;
import com.example.LocalHunt.product.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddProductCommandHandler implements Command<ProductRequest, ProductDTO> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public AddProductCommandHandler(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(ProductRequest request) {
        Category category = (Category) categoryRepository.findByName(request.getCategory())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(request, category);
        productRepository.save(newProduct);
        return ResponseEntity.ok(new ProductDTO(newProduct));
    }
}
