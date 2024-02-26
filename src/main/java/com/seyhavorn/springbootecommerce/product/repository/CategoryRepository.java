package com.seyhavorn.springbootecommerce.product.repository;

import com.seyhavorn.springbootecommerce.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Boolean existsByName(String name);
}
