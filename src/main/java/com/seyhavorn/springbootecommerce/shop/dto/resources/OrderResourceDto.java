package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

import java.util.Date;

@Data
public class OrderResourceDto extends BaseResource {
    private Date orderDate;
    private Integer totalPrice;
}
