package com.seyhavorn.springbootecommerce.authentication.service;

import com.seyhavorn.springbootecommerce.authentication.dto.RoleDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;

import java.util.List;

public interface RoleService {
    Role addRole(String name);

    Boolean addPermissionToRole(Long role_id, Long permission_id);

    Boolean removePermissionFromRole(Long roleId, Long permissionId);

    List<RoleDto> findAll();
}
