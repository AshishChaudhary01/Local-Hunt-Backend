package com.example.LocalHunt.category.commandHandler;

import com.example.LocalHunt.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCategoryCommand {
    private String id;
    private CategoryRequest request;
}
