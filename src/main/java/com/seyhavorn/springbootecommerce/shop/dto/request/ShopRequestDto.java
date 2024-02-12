package com.seyhavorn.springbootecommerce.shop.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class ShopRequestDto {
    private String name;
    private String address;
    private String contact;
    private String email;
    private String logo;
    private String description;
    private Map<String, Object> shop_object;
}
