package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@EnableCaching
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestBody(required = false) FilterRequestDto filterRequestDto,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Customers list", customerService.findAll(page, size, filterRequestDto)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Customer created", customerService.create(customerRequestDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", customerService.findById(customerId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long customerId, @RequestBody() CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", customerService.update(customerRequestDto, customerId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", null));
    }
}
