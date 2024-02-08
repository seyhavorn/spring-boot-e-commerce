package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

@Data
public class ProductResourceDto extends BaseResource {
    private String name;
    private Double price;
    private String imageUrl;
    private Double discount;
    private String description;
}
