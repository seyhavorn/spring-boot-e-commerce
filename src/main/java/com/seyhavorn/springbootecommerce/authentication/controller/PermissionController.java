package com.seyhavorn.springbootecommerce.authentication.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.PermissionDto;
import com.seyhavorn.springbootecommerce.authentication.service.PermissionService;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@EnableCaching
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/add")
    public ResponseEntity<?> addPermission(@RequestBody PermissionDto permissionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Permission added", permissionService.addPermission(permissionDto.getName()))
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse list() {
        return new ApiResponse(true, "Permission added", permissionService.findAll());
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<ApiResponse> getPermissionById(@PathVariable("permissionId") Long permissionId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Success", permissionService.getPermissionById(permissionId))
        );
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<ApiResponse> deletePermission(@PathVariable("permissionId") Long permissionId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Success", permissionService.delete(permissionId))
        );
    }

}
