package com.seyhavorn.springbootecommerce.shop.entity;


import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Integer amount;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}
