package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.seyhavorn.springbootecommerce.authentication.dto.PermissionDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Permission;
import com.seyhavorn.springbootecommerce.authentication.repository.PermissionRepository;
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

    @Override
    public Permission addPermission(String name) {
        if (permissionRepository.existsByName(name)) {
            throw new RuntimeException("Permission already exists");
        }
        Permission permission = new Permission();
        permission.setName(name);
        return permissionRepository.save(permission);
    }

    @Override
    public List<PermissionDto> findAll() {
        List<Permission> permissions = permissionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return permissions.stream()
                .map(permission -> new PermissionDto(permission.getId(), permission.getName()))
                .toList();
    }
}
