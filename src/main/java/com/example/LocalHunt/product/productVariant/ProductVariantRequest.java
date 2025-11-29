package com.example.LocalHunt.product.productVariant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductVariantRequest {
    private String size;
    private Double price;
    private Integer stock;
    private Boolean available;
}
