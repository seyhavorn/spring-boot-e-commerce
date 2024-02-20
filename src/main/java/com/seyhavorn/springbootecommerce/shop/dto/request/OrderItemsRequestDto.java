package com.seyhavorn.springbootecommerce.shop.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseRequest;
import lombok.Data;

@Data
public class OrderItemsRequestDto extends BaseRequest {
    private Long id;

    private Integer quantity;

    private Double price;
    private Long product_id;
}
