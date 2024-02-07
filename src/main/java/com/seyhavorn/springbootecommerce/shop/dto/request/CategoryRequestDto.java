package com.seyhavorn.springbootecommerce.shop.dto.request;

import lombok.Data;

@Data
public class CategoryRequestDto {
    private String name;
    private String description;
    private String imageUrl;
}
