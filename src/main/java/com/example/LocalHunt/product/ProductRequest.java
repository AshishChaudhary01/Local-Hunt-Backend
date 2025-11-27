package com.example.LocalHunt.product;

import com.example.LocalHunt.enums.ProductStatus;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductRequest {
    private String title;
    private String description;
    private Double price;
    private Integer stock;
    private Boolean available;
    private Double lat;
    private Double lng;
    private Boolean pickupAvailable;
    private Boolean deliveryAvailable;
    private ProductStatus status;
    private String imageUrl;
    private String category;
    private String productSize;
}
