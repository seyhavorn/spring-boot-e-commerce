package com.seyhavorn.springbootecommerce.shop.dto.resources;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

//@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResourceDto extends BaseResource {
    private String name;
    private Integer price;
    private String imageUrl;
    private Integer discount;
    private String description;
    private Long category_id;
}
