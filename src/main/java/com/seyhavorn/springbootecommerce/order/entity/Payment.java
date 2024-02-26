package com.seyhavorn.springbootecommerce.order.entity;


import com.seyhavorn.springbootecommerce.helper.AuditableEntity;
import com.seyhavorn.springbootecommerce.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
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
