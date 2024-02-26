package com.seyhavorn.springbootecommerce.product.dto.request;

import lombok.Data;

@Data
public class CategoryRequestDto {
    private String name;
    private String description;
    private String imageUrl;
}
