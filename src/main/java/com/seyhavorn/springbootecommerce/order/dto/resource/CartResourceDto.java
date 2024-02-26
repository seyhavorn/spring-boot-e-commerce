package com.seyhavorn.springbootecommerce.order.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
public class CartResourceDto extends BaseResource {
    private Integer quantity;
    private Long customer_id;
}
