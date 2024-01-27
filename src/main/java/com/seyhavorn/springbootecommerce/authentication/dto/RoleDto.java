package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private List<PermissionDto> permissions;
}
