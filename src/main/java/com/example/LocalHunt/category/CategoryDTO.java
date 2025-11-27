package com.example.LocalHunt.category;

import lombok.Data;

@Data
public class CategoryDTO {
    private String id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
