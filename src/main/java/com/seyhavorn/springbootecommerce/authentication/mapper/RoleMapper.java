package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.dto.resource.PermissionResource;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.RoleResource;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMapper {
    public RoleResource roleToRoleResource(Role role) {
        RoleResource roleResource = new RoleResource();
        BeanUtils.copyProperties(role, roleResource);

        //Permission List
        List<PermissionResource> PermissionResources = role.getPermissions()
                .stream()
                .map(permission -> {
                    PermissionResource permissionResource = new PermissionResource();
                    BeanUtils.copyProperties(permission, permissionResource);
                    return permissionResource;
                }).toList();

        roleResource.setPermissions(PermissionResources);

        return roleResource;
    }
}
