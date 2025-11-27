package com.example.LocalHunt.product;

public enum ProductSortBy {
    category("category"),
    nameOrDescription("nameOrDescription"),
    price("price");
//    location("location");

    private final String value;

    ProductSortBy(String value) {
        this.value = value;
    }

    public static ProductSortBy fromValue(String value) {
        for (ProductSortBy sortBy: ProductSortBy.values()) {
            if (sortBy.value.equalsIgnoreCase(value)) {
                return sortBy;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
