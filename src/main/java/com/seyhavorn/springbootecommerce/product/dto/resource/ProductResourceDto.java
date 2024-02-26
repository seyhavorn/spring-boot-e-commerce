package com.seyhavorn.springbootecommerce.product.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class ProductResourceDto extends BaseResource {
    private String name;
    private Integer price;
    private String imageUrl;
    private Integer discount;
    private String description;
    private Long category_id;
}
