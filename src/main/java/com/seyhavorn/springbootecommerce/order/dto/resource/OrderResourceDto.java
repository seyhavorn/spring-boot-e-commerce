package com.seyhavorn.springbootecommerce.order.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

import java.util.Date;

@Data
public class OrderResourceDto extends BaseResource {
    private Date orderDate;
    private Integer totalPrice;
}
