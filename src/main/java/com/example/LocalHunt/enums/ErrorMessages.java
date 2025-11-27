package com.example.LocalHunt.enums;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_NOT_VALID("Product not valid"),
    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_ALREADY_EXISTS("Category already exists"),
    CATEGORY_DELETION_CONFLICT("Cannot delete category because one or more products are associated with it."),
    VENDOR_NOT_FOUND("Vendor not found"),
    USER_NOT_FOUND("User not found"),
    INVALID_CREDENTIALS("Invalid credentials"),
    UNAUTHORIZED("Unauthorized access"),
    FORBIDDEN("Operation not allowed"),
    ORDER_NOT_FOUND("Order not found"),
    PAYMENT_FAILED("Payment processing failed");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
