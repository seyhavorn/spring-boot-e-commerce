package com.seyhavorn.springbootecommerce.authentication.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRoleDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.RoleResource;

import java.util.List;

public interface RoleService {
    RoleResource addRole(String name);

    RoleResource findRoleById(Long roleId);

    Boolean addPermissionToRole(Long role_id, Long permission_id);

    Boolean removePermissionFromRole(Long roleId, Long permissionId);

    List<RoleResource> findAll();

    List<RoleResource> getAllRoles(FilterRoleDto filterRoleDto);
}
