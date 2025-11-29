package com.example.LocalHunt.product.productVariant;

import lombok.Data;

@Data
public class ProductVariantDTO {
    private String id;
    private String size;
    private Double price;
    private Integer stock;
    private Boolean available;

    public ProductVariantDTO(ProductVariant variant) {
        this.id = variant.getId();
        this.size = variant.getSize();
        this.price = variant.getPrice();
        this.stock = variant.getStock();
        this.available = variant.getAvailable();
    }
}
