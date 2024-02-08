package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@EnableCaching
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Customer created", categoryService.create(categoryRequestDto)));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) FilterRequestDto filterRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Categories list", categoryService.findAll(page, size, filterRequestDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", categoryService.findById(categoryId))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long categoryId, @RequestBody() CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Category has updated", categoryService.update(categoryRequestDto, categoryId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", null)
        );
    }

}
