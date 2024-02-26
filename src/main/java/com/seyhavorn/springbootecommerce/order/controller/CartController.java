package com.seyhavorn.springbootecommerce.order.controller;

import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.order.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.order.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
@EnableCaching
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CartRequestDto cartRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Cart has been created", cartService.create(cartRequestDto))
        );
    }

    @GetMapping("/cartByCustomerId/:{id}")
    public ResponseEntity<?> findCartByCustomerId(@PathVariable("id") Long id) {
        System.out.println("cart By Customer Id");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Cart By Customer Id: " + id, cartService.findCartByCustomerId(id))
        );
    }

}


