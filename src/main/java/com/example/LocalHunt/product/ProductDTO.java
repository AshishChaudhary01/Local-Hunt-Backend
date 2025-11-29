package com.example.LocalHunt.product;

import com.example.LocalHunt.enums.ProductStatus;
import com.example.LocalHunt.product.productVariant.ProductVariantDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private String id;
    private String title;
    private String description;
    private Double lat;
    private Double lng;
    private Boolean pickupAvailable;
    private Boolean deliveryAvailable;
    private ProductStatus status;
    private String imageUrl;
    private String categoryId;
    private String category;
    private List<ProductVariantDTO> variants;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.lat = product.getLat();
        this.lng = product.getLng();
        this.pickupAvailable = product.getPickupAvailable();
        this.deliveryAvailable = product.getDeliveryAvailable();
        this.status = product.getStatus();
        this.imageUrl = product.getImageUrl();
        this.categoryId = product.getCategory().getId();
        this.category = product.getCategory().getName();
        this.variants = product.getVariants()
                .stream()
                .map(ProductVariantDTO::new)
                .toList();
    }
}
