package com.example.LocalHunt.category;

import com.example.LocalHunt.category.commandHandler.AddCategoryCommandHandler;
import com.example.LocalHunt.category.commandHandler.DeleteCategoryCommandHandler;
import com.example.LocalHunt.category.commandHandler.UpdateCategoryCommand;
import com.example.LocalHunt.category.commandHandler.UpdateCategoryCommandHandler;
import com.example.LocalHunt.category.queryHandler.GetCategoriesQueryHandler;
import com.example.LocalHunt.category.queryHandler.GetCategoryByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final AddCategoryCommandHandler addCategoryCommandHandler;
    private final GetCategoriesQueryHandler getCategoriesQueryHandler;
    private final GetCategoryByIdQueryHandler getCategoryByIdQueryHandler;
    private final UpdateCategoryCommandHandler updateCategoryCommandHandler;
    private final DeleteCategoryCommandHandler deleteCategoryCommandHandler;

    public CategoryController(AddCategoryCommandHandler addCategoryCommandHandler, GetCategoriesQueryHandler getCategoriesQueryHandler, GetCategoryByIdQueryHandler getCategoryByIdQueryHandler, UpdateCategoryCommandHandler updateCategoryCommandHandler, DeleteCategoryCommandHandler deleteCategoryCommandHandler) {
        this.addCategoryCommandHandler = addCategoryCommandHandler;
        this.getCategoriesQueryHandler = getCategoriesQueryHandler;
        this.getCategoryByIdQueryHandler = getCategoryByIdQueryHandler;
        this.updateCategoryCommandHandler = updateCategoryCommandHandler;
        this.deleteCategoryCommandHandler = deleteCategoryCommandHandler;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryRequest request) {
        return addCategoryCommandHandler.execute(request);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id) {
        return getCategoryByIdQueryHandler.execute(id);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return getCategoriesQueryHandler.execute(null);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String id, @RequestBody CategoryRequest request
    ) {
        return updateCategoryCommandHandler.execute(
                new UpdateCategoryCommand(id, request)
        );
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        return deleteCategoryCommandHandler.execute(id);
    }
}
