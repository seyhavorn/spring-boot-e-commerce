package com.seyhavorn.springbootecommerce.order.controller;


import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.order.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.order.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderItems")
@AllArgsConstructor
@EnableCaching
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @PostMapping
    @CacheEvict(value = "orderItems", allEntries = true)
    public ResponseEntity<?> create(@RequestBody OrderItemsRequestDto orderItemsRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Order Items created", orderItemsService.create(orderItemsRequestDto))
        );
    }

    @PostMapping("/list")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) FilterRequestDto filterRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Order Items list", orderItemsService.findAll(page, size, filterRequestDto))
        );
    }
}
