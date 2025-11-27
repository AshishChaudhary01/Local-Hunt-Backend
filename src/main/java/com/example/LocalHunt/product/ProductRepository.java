package com.example.LocalHunt.product;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:nameOrDescription is null or p.title like %:nameOrDescription% or p.description like %:nameOrDescription) and " +
            "(:category is null or p.category.name = :category)")
    List<Product> findByNameOrDescriptionAndCategory(
            String nameOrDescription,
            String category,
            Sort sort
    );

    boolean existsProductByCategory_Name(String categoryName);

    List<Product> findAllByCategory_Name(String categoryName);

    boolean existsProductByCategory_Id(String categoryId);
}
