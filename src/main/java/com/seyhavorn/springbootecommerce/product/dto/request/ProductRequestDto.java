package com.seyhavorn.springbootecommerce.product.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private Integer price = 0;
    private String imageUrl;
    private Integer discount = 0;
    private String description;
    private Long category_id;
}
