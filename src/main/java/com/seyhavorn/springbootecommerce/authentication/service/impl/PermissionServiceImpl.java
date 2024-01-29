package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.seyhavorn.springbootecommerce.authentication.entity.Permission;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.mapper.PermissionMapper;
import com.seyhavorn.springbootecommerce.authentication.repository.PermissionRepository;
import com.seyhavorn.springbootecommerce.authentication.resource.PermissionResource;
import com.seyhavorn.springbootecommerce.authentication.service.PermissionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionResource addPermission(String name) {
        if (permissionRepository.existsByName(name)) {
            throw new RuntimeException("Permission already exists");
        }
        Permission permission = new Permission();
        permission.setName(name);
        return permissionMapper.permissionToPermissionResource(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResource> findAll() {
        List<Permission> permissions = permissionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return permissions.stream()
                .map(permissionMapper::permissionToPermissionResource)
                .toList();
    }

    @Override
    public PermissionResource getPermissionById(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found!"));
        return permissionMapper.permissionToPermissionResource(permission);
    }

    @Override
    public Boolean delete(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found!"));

        for (Role role : permission.getRoles()) {
            role.getPermissions().remove(permission);
        }
        permissionRepository.delete(permission);
        return true;
    }
}
