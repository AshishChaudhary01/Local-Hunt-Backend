package com.example.LocalHunt.product.commandHandler;

import com.example.LocalHunt.Command;
import com.example.LocalHunt.exceptions.ProductNotFoundException;
import com.example.LocalHunt.product.Product;
import com.example.LocalHunt.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<String, Void> {

    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new ProductNotFoundException();
    }
}
