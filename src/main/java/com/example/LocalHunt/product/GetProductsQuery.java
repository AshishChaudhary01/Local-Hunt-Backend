package com.example.LocalHunt.product;

import lombok.Getter;

@Getter
public class GetProductsQuery {
    private String category;
    private String nameOrDescription;
    private ProductSortBy productSortBy;

    public GetProductsQuery(String category, String nameOrDescription, ProductSortBy productSortBy) {
        this.category = category;
        this.nameOrDescription = nameOrDescription;
        this.productSortBy = productSortBy;
    }
}
