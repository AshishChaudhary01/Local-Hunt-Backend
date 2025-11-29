package com.example.LocalHunt.product.productVariant;

import com.example.LocalHunt.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36)
    private String id;

    private String size;

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

    @ManyToOne
    private Product product;

    public ProductVariant(ProductVariantRequest request, Product product) {
        this.size = request.getSize();
        this.price = request.getPrice();
        this.stock = request.getStock();
        this.available = request.getAvailable();
        this.product = product;
    }
}
