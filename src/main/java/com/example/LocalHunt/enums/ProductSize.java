package com.example.LocalHunt.enums;

public enum ProductSize {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL"),
    EXTRA_EXTRA_LARGE("XXL"),
    EXTRA_EXTRA_EXTRA_LARGE("XXXL");

    private final String productSize;

    ProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductSize() {
        return productSize;
    }
}
