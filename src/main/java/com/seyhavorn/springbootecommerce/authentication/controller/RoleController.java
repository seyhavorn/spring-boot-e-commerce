package com.seyhavorn.springbootecommerce.authentication.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRoleDto;
import com.seyhavorn.springbootecommerce.authentication.dto.RoleDto;
import com.seyhavorn.springbootecommerce.authentication.dto.request.AddPermissionRoleRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.request.RoleAndUserActionRequestDto;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
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
    private final UserService userService;

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
    public ResponseEntity<?> addPermissionToRole(@RequestBody AddPermissionRoleRequestDto addPermissionRoleRequest) {
        Boolean addPermissionToRole = roleService.addPermissionToRole(addPermissionRoleRequest.getRole_id(), addPermissionRoleRequest.getPermission_id());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(addPermissionToRole, "Permission added to roles", null)
        );
    }

    @PostMapping("/removePermissionFromRole")
    public ResponseEntity<?> removePermissionFromRole(@RequestBody AddPermissionRoleRequestDto addPermissionRoleRequest) {
        Boolean removePermissionFromRole = roleService.removePermissionFromRole(addPermissionRoleRequest.getRole_id(), addPermissionRoleRequest.getPermission_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(removePermissionFromRole, "Permission removed from role", null)
        );
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleAndUserActionRequestDto roleAndUserActionRequest) {
        Boolean addRoleToUser = userService.addRoleToUser(roleAndUserActionRequest.getUser_id(), roleAndUserActionRequest.getRole_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(addRoleToUser, "Role added to user", null)
        );
    }

    @PostMapping("/removeRoleFromUser")
    public ResponseEntity<?> removeRoleFromUser(@RequestBody RoleAndUserActionRequestDto roleAndUserActionRequest) {
        Boolean removeRoleFromUser = userService.removeRoleFromUser(roleAndUserActionRequest.getUser_id(), roleAndUserActionRequest.getUser_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(removeRoleFromUser, "Role removed from user", null)
        );
    }

    @PostMapping
    public ResponseEntity<?> findAll(@RequestBody(required = false) FilterRoleDto filterRoleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Roles List", roleService.getAllRoles(filterRoleDto))
        );
    }
}
