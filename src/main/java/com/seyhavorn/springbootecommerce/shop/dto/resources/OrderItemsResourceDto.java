package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class OrderItemsResourceDto extends BaseResource {
    private Long id;

    private Integer quantity;

    private Double price;

    private Long product_id;

}
