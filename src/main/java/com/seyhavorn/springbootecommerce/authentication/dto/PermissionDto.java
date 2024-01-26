package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionDto {
    private Long id;
    private String name;
}
