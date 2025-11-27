package com.example.LocalHunt.product.commandHandler;

import com.example.LocalHunt.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProductCommand {
    private String id;
    private ProductRequest request;
}
