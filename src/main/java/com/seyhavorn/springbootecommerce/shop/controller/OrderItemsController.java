package com.seyhavorn.springbootecommerce.shop.controller;


import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.OrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
