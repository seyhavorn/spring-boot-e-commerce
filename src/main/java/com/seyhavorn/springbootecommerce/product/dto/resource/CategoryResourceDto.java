package com.seyhavorn.springbootecommerce.product.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class CategoryResourceDto extends BaseResource {
    private String name;
    private String description;
    private String imageUrl;
}
