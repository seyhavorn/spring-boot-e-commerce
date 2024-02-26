package com.seyhavorn.springbootecommerce.shop.repository;

import com.seyhavorn.springbootecommerce.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Boolean existsByUsername(String username);

}
