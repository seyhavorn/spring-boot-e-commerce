package com.seyhavorn.springbootecommerce.shop.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import com.seyhavorn.springbootecommerce.shop.dto.request.AssignShopToUserReqDTO;
import com.seyhavorn.springbootecommerce.shop.dto.request.FetchUsersByShopId;
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
                new ApiResponse(true, "Shop created", shopService.create(shopRequestDto)));
    }

    @PostMapping("/list")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) FilterRequestDto filterRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "shop list", shopService.findAll(page, size, filterRequestDto))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long shopId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", shopService.findById(shopId))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ShopRequestDto shopRequestDto) {
        shopRequestDto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "shop has updated", shopService.update(shopRequestDto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long shopId) {
        shopService.deleteShop(shopId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", null)
        );
    }

    @PostMapping("/assignShopToUser")
    public ResponseEntity<?> assignShopToUser(@RequestBody AssignShopToUserReqDTO assignShopToUserReqDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Shop assigned successfully",
                        shopService.assignShopToUser(assignShopToUserReqDTO.getUserId(), assignShopToUserReqDTO.getShopId()))
        );
    }

    @PostMapping("/usersByShopId")
    public ResponseEntity<?> fetchUsersByShopId(@RequestBody FetchUsersByShopId fetchUsersByShopId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "Users By Shop ID: " + fetchUsersByShopId.getShopId(),
                        shopService.fetchUsersByShopId(fetchUsersByShopId))
        );
    }

}
