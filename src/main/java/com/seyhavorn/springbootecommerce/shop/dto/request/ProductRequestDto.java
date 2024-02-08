package com.seyhavorn.springbootecommerce.shop.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private Double price;
    private String imageUrl;
    private Double discount;
    private String description;
}
