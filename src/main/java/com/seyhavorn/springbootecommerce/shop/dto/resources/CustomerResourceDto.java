package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class CustomerResourceDto extends BaseResource {
    private String name;
    private String username;
    private String email;
}
