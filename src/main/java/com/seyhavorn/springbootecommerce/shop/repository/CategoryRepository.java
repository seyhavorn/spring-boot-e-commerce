package com.seyhavorn.springbootecommerce.shop.repository;

import com.seyhavorn.springbootecommerce.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String nsame);
}
