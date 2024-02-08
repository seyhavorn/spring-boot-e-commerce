package com.seyhavorn.springbootecommerce.shop.repository;

import com.seyhavorn.springbootecommerce.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Boolean existsByName(String name);
}