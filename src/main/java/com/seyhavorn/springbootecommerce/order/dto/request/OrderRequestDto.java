package com.seyhavorn.springbootecommerce.order.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class OrderRequestDto extends BaseRequest {
    private Date orderDate;
    private Integer totalPrice;
}
