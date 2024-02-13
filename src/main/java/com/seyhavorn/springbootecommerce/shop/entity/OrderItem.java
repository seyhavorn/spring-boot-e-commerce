package com.seyhavorn.springbootecommerce.shop.entity;

import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double price;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
