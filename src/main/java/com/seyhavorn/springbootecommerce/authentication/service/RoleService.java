package com.seyhavorn.springbootecommerce.authentication.service;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.resource.RoleResource;

import java.util.List;

public interface RoleService {
    RoleResource addRole(String name);

    RoleResource findRoleById(Long roleId);

    Boolean addPermissionToRole(Long role_id, Long permission_id);

    Boolean removePermissionFromRole(Long roleId, Long permissionId);

    List<RoleResource> findAll();
}
