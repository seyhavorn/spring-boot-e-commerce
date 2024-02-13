package com.seyhavorn.springbootecommerce.shop.repository;

import com.seyhavorn.springbootecommerce.shop.entity.Branch;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long>, JpaSpecificationExecutor<Branch> {
    Boolean existsByName(String name);

    List<Branch> findBranchByShop(Shop shop);
}
