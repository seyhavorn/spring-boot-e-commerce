package com.seyhavorn.springbootecommerce.shop.controller;


import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShipmentRequestDto;
import com.seyhavorn.springbootecommerce.shop.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipment")
@AllArgsConstructor
@EnableCaching
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ShipmentRequestDto shipmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Shipment created", shipmentService.create(shipmentRequestDto))
        );
    }
}
