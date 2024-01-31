package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRoleDto {
    private String id;
    private String name;
    private String createdAt;
    private String updatedAt;
}
