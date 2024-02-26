package com.seyhavorn.springbootecommerce.product.repository;

import com.seyhavorn.springbootecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> getProductByCategoryId(Long categoryId);
}
