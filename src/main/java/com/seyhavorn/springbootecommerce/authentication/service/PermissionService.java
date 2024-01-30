package com.seyhavorn.springbootecommerce.authentication.service;

import com.seyhavorn.springbootecommerce.authentication.resource.PermissionResource;

import java.util.List;

public interface PermissionService {
    PermissionResource addPermission(String name);

    List<PermissionResource> findAll();

    PermissionResource getPermissionById(Long permissionId);

    Boolean delete(Long permissionId);
}
