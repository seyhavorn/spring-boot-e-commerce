package com.seyhavorn.springbootecommerce.authentication.dto.record;

import com.seyhavorn.springbootecommerce.authentication.entity.Permission;

import java.util.Set;

public record ListRolesDto(
        Long id,
        String name,
        Set<Permission> permissions
) {
}
