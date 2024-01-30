package com.seyhavorn.springbootecommerce.authentication.controller;

import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "success", userService.findUserById(userId))
        );
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(true, "User list", userService.findAll())
        );
    }
}
