package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@EnableCaching
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestBody(required = false) FilterRequestDto filterRequestDto,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Customers list", productService.findAll(page, size, filterRequestDto)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Customer created", productService.create(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", productService.findById(productId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long productId, @RequestBody() ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", productService.update(productRequestDto, productId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", null));
    }
}
