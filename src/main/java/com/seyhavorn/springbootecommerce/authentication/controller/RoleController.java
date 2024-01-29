package com.seyhavorn.springbootecommerce.authentication.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.RoleDto;
import com.seyhavorn.springbootecommerce.authentication.request.AddPermissionRoleRequest;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRole(@RequestBody @Valid RoleDto roleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Role has been added", roleService.addRole(roleDto.getName()))
        );
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse> getRoleById(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Success", roleService.findRoleById(roleId)));
    }

    @PostMapping("/addPermissionToRole")
    public ResponseEntity<?> addPermissionToRole(@RequestBody AddPermissionRoleRequest addPermissionRoleRequest) {
        Boolean addPermissionToRole = roleService.addPermissionToRole(addPermissionRoleRequest.getRole_id(), addPermissionRoleRequest.getPermission_id());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(addPermissionToRole, "Permission added to roles", null)
        );
    }

    @PostMapping("/removePermissionFromRole")
    public ResponseEntity<?> removePermissionFromRole(@RequestBody AddPermissionRoleRequest addPermissionRoleRequest) {
        Boolean removePermissionFromRole = roleService.removePermissionFromRole(addPermissionRoleRequest.getRole_id(), addPermissionRoleRequest.getPermission_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(removePermissionFromRole, "Permission removed from role", null)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Roles List", roleService.findAll())
        );
    }
}
