package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
@AllArgsConstructor
@EnableCaching
public class ShopController {
    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ShopRequestDto shopRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Customer created", shopService.create(shopRequestDto)));
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) FilterRequestDto filterRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Categories list", shopService.findAll(page, size, filterRequestDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long shopId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", shopService.findById(shopId))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long shopId, @RequestBody() ShopRequestDto shopRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Category has updated", shopService.update(shopRequestDto, shopId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long shopId) {
        shopService.deleteShop(shopId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", null)
        );
    }

}
