package com.seyhavorn.springbootecommerce.order.entity;

import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import com.seyhavorn.springbootecommerce.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_items")
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
