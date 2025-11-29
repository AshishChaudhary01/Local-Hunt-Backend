package com.example.LocalHunt.product;

import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.enums.ProductStatus;
import com.example.LocalHunt.product.productVariant.ProductVariant;
import com.example.LocalHunt.product.productVariant.ProductVariantRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36)
    private String id;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be blank")
    @Column(name = "title")
    private String title;

    //Add vendorId later

    @Size(min = 10, max = 99, message = "Description must be within 10-99 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "pickup_available")
    private Boolean pickupAvailable;

    @Column(name = "delivery_available")
    private Boolean deliveryAvailable;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();

    //Helper Constructor method to create a new product
    public Product(ProductRequest request, Category category) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.lat = request.getLat();
        this.lng = request.getLng();
        this.pickupAvailable = request.getPickupAvailable();
        this.deliveryAvailable = request.getDeliveryAvailable();
        this.status = request.getStatus();
        this.imageUrl = request.getImageUrl();
        this.category = category;
    }

    //Helper Constructor method to update an existing product
    public void updateFromRequest(ProductRequest request, Category category) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.lat = request.getLat();
        this.lng = request.getLng();
        this.pickupAvailable = request.getPickupAvailable();
        this.deliveryAvailable = request.getDeliveryAvailable();
        this.status = request.getStatus();
        this.imageUrl = request.getImageUrl();
        this.category = category;

        // Safe way: remove old variants safely
        List<ProductVariant> existingVariants = new ArrayList<>(this.variants);
        for (ProductVariant v : existingVariants) {
            v.setProduct(null); // detach from parent
            this.variants.remove(v);
        }

        // Add new variants from request (if any)
        if (request.getVariants() != null) {
            for (ProductVariantRequest pvr : request.getVariants()) {
                ProductVariant variant = new ProductVariant(pvr, this);
                this.variants.add(variant);
            }
        }
    }
}
