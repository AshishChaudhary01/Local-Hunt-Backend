package com.example.LocalHunt.product.queryHandler;

import com.example.LocalHunt.Query;
import com.example.LocalHunt.product.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductsQueryHandler implements Query<GetProductsQuery, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public GetProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQuery query) {

        Sort productSort = defineSort(query.getProductSortBy());

        List<Product> productList = productRepository
                .findByNameOrDescriptionAndCategory(
                        query.getNameOrDescription(),
                        query.getCategory(),
                        productSort
                );
        return ResponseEntity.ok(productList
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList()));
    }

    private Sort defineSort(ProductSortBy productSortBy) {
        if (productSortBy == null) {
            return Sort.unsorted();
        }

        ProductSortBy sortBy = ProductSortBy.valueOf(productSortBy.getValue());
        return Sort.by(String.valueOf(sortBy));
    }
}
