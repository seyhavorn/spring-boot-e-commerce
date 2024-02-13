package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.BranchRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
@AllArgsConstructor
@EnableCaching
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BranchRequestDto branchRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Branch created", branchService.create(branchRequestDto)));
    }

    @PostMapping("/list")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) FilterRequestDto filterRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Branches list", branchService.findAll(page, size, filterRequestDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long branchId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", branchService.findById(branchId))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long branchId, @RequestBody() BranchRequestDto branchRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Branch has updated", branchService.update(branchRequestDto, branchId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long branchId) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", null)
        );
    }

    @GetMapping("/branchesByShopId/{shopId}")
    public ResponseEntity<?> getBranchesByShopId(@PathVariable("shopId") Long shopId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Branches by Shop Id: " + shopId, branchService.branchByShopId(shopId))
        );
    }

}
