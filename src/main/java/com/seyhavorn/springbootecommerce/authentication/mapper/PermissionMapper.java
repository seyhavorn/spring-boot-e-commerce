package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.entity.Permission;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.PermissionResource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PermissionMapper {
    public PermissionResource permissionToPermissionResource(Permission permission) {
        PermissionResource permissionResource = new PermissionResource();
        BeanUtils.copyProperties(permission, permissionResource);
        return permissionResource;
    }
}
