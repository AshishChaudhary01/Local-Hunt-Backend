package com.example.LocalHunt.product;

import com.example.LocalHunt.product.commandHandler.AddProductCommandHandler;
import com.example.LocalHunt.product.commandHandler.DeleteProductCommandHandler;
import com.example.LocalHunt.product.commandHandler.UpdateProductCommand;
import com.example.LocalHunt.product.commandHandler.UpdateProductCommandHandler;
import com.example.LocalHunt.product.queryHandler.GetProductByIdQueryHandler;
import com.example.LocalHunt.product.queryHandler.GetProductsQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final AddProductCommandHandler addProductCommandHandler;
    private final GetProductByIdQueryHandler getProductByIdQueryHandler;
    private final GetProductsQueryHandler getProductsQueryHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;

    public ProductController(
            AddProductCommandHandler addProductCommandHandler,
            GetProductByIdQueryHandler getProductByIdQueryHandler,
            GetProductsQueryHandler getProductsQueryHandler, UpdateProductCommandHandler updateProductCommandHandler, DeleteProductCommandHandler deleteProductCommandHandler) {
        this.addProductCommandHandler = addProductCommandHandler;
        this.getProductByIdQueryHandler = getProductByIdQueryHandler;
        this.getProductsQueryHandler = getProductsQueryHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductRequest request) {
        return addProductCommandHandler.execute(request);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return getProductByIdQueryHandler.execute(id);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String orderBy
    ) {
        return getProductsQueryHandler.execute(new GetProductsQuery(
                category,
                nameOrDescription,
                ProductSortBy.fromValue(orderBy)
        ));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {
        return updateProductCommandHandler.execute(new UpdateProductCommand(id, request));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        return deleteProductCommandHandler.execute(id);
    }
}
