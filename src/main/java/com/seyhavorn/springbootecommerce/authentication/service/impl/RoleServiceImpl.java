package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRoleDto;
import com.seyhavorn.springbootecommerce.authentication.dto.PermissionDto;
import com.seyhavorn.springbootecommerce.authentication.dto.RoleDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.RoleResource;
import com.seyhavorn.springbootecommerce.authentication.entity.Permission;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.mapper.RoleMapper;
import com.seyhavorn.springbootecommerce.authentication.repository.PermissionRepository;
import com.seyhavorn.springbootecommerce.authentication.repository.RoleRepository;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import com.seyhavorn.springbootecommerce.authentication.specifications.RoleFilterSpecifications;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResource addRole(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RuntimeException("Role is already exists");
        }
        Role role = new Role();
        role.setName(name);
        return roleMapper.roleToRoleResource(roleRepository.save(role));
    }

    @Override
    @Cacheable(value = "roles", key = "#roleId")
    public RoleResource findRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.roleToRoleResource(role);
    }

    @Override
    public Boolean addPermissionToRole(Long role_id, Long permission_id) {

        Role role = roleRepository.findById(role_id).orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = permissionRepository.findById(permission_id).orElseThrow(() -> new RuntimeException("Permission not found"));
        if (role.hasPermission(permission)) {
            return false;
        }
        role.assignPermissionToRole(permission);
        return true;
    }

    @Override
    public Boolean removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"));
        role.removePermissionFromRole(permission);
        return true;
    }

    @Override
    @Cacheable("roles")
    public List<RoleResource> findAll() {
        List<Role> roles = roleRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return roles.stream().map(roleMapper::roleToRoleResource).toList();
    }

    //Method didn't use Mapper:
    private List<RoleDto> listRole() {
        List<Role> roles = roleRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return roles.stream().map(role -> new RoleDto(role.getId(), role.getName(), role.getPermissions().stream().map(permission -> new PermissionDto(permission.getId(), permission.getName())).toList())).toList();
    }

    @Override
    @Cacheable("roles")
    public List<RoleResource> getAllRoles(FilterRoleDto filterRoleDto) {
        Specification<Role> spec = RoleFilterSpecifications.filter(filterRoleDto);
        List<Role> roles = roleRepository.findAll(spec);
        return roles.stream().map(roleMapper::roleToRoleResource).toList();
    }

}
