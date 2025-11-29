package com.example.LocalHunt.product;

import com.example.LocalHunt.enums.ProductStatus;
import com.example.LocalHunt.product.productVariant.ProductVariantRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class ProductRequest {
    private String title;
    private String description;
    private Double lat;
    private Double lng;
    private Boolean pickupAvailable;
    private Boolean deliveryAvailable;
    private ProductStatus status;
    private String imageUrl;
    private String category;
    private final List<ProductVariantRequest> variants = new ArrayList<>();
}
