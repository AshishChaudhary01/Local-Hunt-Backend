package com.example.LocalHunt.product;

import com.example.LocalHunt.category.Category;
import com.example.LocalHunt.enums.ProductSize;
import com.example.LocalHunt.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @PositiveOrZero(message = "Price cannot be negative")
    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Min(value = 0, message = "Stock count cannot be negative")
    @Max(value = 9999, message = "Stock count cannot be more than 9999")
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "available")
    private Boolean available;

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

    public Product(ProductRequest request, Category category) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.stock = request.getStock();
        this.available = request.getAvailable();
        this.lat = request.getLat();
        this.lng = request.getLng();
        this.pickupAvailable = request.getPickupAvailable();
        this.deliveryAvailable = request.getDeliveryAvailable();
        this.status = request.getStatus();
        this.imageUrl = request.getImageUrl();
        this.category = category;
    }
}
