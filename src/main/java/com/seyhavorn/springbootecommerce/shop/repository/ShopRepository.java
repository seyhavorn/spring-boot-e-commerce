package com.seyhavorn.springbootecommerce.shop.repository;


import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopRepository extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {
    Boolean existsByName(String name);
}
