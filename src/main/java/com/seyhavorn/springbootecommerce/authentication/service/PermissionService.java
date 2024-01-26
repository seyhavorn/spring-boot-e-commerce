package com.seyhavorn.springbootecommerce.authentication.service;

import com.seyhavorn.springbootecommerce.authentication.dto.PermissionDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Permission;

import java.util.List;

public interface PermissionService {
    Permission addPermission(String name);

    List<PermissionDto> findAll();
}
