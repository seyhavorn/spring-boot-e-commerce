package com.seyhavorn.springbootecommerce.shop.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartRequestDto extends BaseResource {
    private String name;
    private Integer quantity;
    private Long customer_id;
}
