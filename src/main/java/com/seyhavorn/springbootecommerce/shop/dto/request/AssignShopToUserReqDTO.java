package com.seyhavorn.springbootecommerce.shop.dto.request;

import lombok.Data;

@Data
public class AssignShopToUserReqDTO {
    private Long userId;
    private Long shopId;
}
