package com.example.LocalHunt.product.queryHandler;

import com.example.LocalHunt.exceptions.ProductNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductDTO;
import com.example.LocalHunt.product.ProductRepository;
import com.example.LocalHunt.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductByIdQueryHandler implements Query<String, ProductDTO> {
    private final ProductRepository productRepository;

    public GetProductByIdQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }
        //Later implement proper exception handling
        throw new ProductNotFoundException();
    }
}
