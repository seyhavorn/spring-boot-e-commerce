package com.seyhavorn.springbootecommerce.shop.dto.request;

import lombok.Data;

@Data
public class FetchUsersByShopId {
    private Long shopId;
    private Integer size = 10;
    private Integer page = 0;
}
