package com.seyhavorn.springbootecommerce.order.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class OrderItemsResourceDto extends BaseResource {
    private Long id;

    private Integer quantity;

    private Double price;

    private Long product_id;

}
