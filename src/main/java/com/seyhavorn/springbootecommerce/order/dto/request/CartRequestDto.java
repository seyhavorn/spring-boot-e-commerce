package com.seyhavorn.springbootecommerce.order.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartRequestDto extends BaseRequest {
    private Integer quantity;
    private Long customer_id;
}
