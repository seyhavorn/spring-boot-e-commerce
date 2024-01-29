package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.resource.PermissionResource;
import com.seyhavorn.springbootecommerce.authentication.resource.RoleResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleMapper {
    private final PermissionMapper permissionMapper;

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
        roleResource.setName(role.getName().toUpperCase());

        return roleResource;
    }
}
