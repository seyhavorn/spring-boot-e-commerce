package com.seyhavorn.springbootecommerce.shop.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseRequest;
import lombok.Data;

import java.util.Map;

@Data
public class ShopRequestDto extends BaseRequest {
    private Long id;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String logo;
    private String description;
    private Map<String, Object> shop_object;
}