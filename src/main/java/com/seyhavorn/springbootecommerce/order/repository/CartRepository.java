package com.seyhavorn.springbootecommerce.order.repository;

import com.seyhavorn.springbootecommerce.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
    List<Cart> findCartsByCustomer_Id(Long customer_id);
}
