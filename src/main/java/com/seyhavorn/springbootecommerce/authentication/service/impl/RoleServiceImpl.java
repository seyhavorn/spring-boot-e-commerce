package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.seyhavorn.springbootecommerce.authentication.dto.PermissionDto;
import com.seyhavorn.springbootecommerce.authentication.dto.RoleDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Permission;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.repository.PermissionRepository;
import com.seyhavorn.springbootecommerce.authentication.repository.RoleRepository;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public Role addRole(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RuntimeException("Role is already exists");
        }
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Override
    public Boolean addPermissionToRole(Long role_id, Long permission_id) {
        try {
            Role role = roleRepository.findById(role_id).orElseThrow(() -> new RuntimeException("Role not found"));
            Permission permission = permissionRepository.findById(permission_id).orElseThrow(() -> new RuntimeException("Permission not found"));
            if (role.hasPermission(permission)) {
                return false;
            }
            role.assignPermissionToRole(permission);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean removePermissionFromRole(Long roleId, Long permissionId) {
        try {
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"));
            role.removePermissionFromRole(permission);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return roles.stream()
                .map(role -> new RoleDto(
                        role.getId(),
                        role.getName(),
                        role.getPermissions()
                                .stream()
                                .map(permission ->
                                        new PermissionDto(permission.getId(), permission.getName())
                                ).toList()
                )).toList();
    }

}
