package com.example.LocalHunt.product;

import com.example.LocalHunt.enums.ProductStatus;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;
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
    private String categoryId;
    private String category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.available = product.getAvailable();
        this.lat = product.getLat();
        this.lng = product.getLng();
        this.pickupAvailable = product.getPickupAvailable();
        this.deliveryAvailable = product.getDeliveryAvailable();
        this.status = product.getStatus();
        this.imageUrl = product.getImageUrl();
        this.categoryId = product.getCategory().getId();
        this.category = product.getCategory().getName();
    }
}
