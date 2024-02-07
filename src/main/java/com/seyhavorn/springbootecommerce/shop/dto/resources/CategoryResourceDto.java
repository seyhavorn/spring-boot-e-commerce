package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class CategoryResourceDto extends BaseResource {
    private String name;
    private String description;
    private String imageUrl;
}
