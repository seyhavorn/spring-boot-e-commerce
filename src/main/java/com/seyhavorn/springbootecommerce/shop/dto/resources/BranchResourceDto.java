package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchResourceDto extends BaseResource {
    private String name;
    private String address;
    private String contact;
    private String email;
    private String logo;
    private String description;
    private Map<String, Object> shop_object;
}
