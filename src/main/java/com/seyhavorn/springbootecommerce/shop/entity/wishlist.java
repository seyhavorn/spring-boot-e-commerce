package com.seyhavorn.springbootecommerce.shop.entity;

import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "wishlist")
public class wishlist extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
