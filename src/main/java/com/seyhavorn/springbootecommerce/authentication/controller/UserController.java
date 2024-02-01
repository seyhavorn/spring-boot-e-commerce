package com.seyhavorn.springbootecommerce.authentication.controller;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterUserDto;
import com.seyhavorn.springbootecommerce.authentication.dto.UserFilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.request.FindUserByUsername;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import com.seyhavorn.springbootecommerce.helper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "success", userService.findUserById(userId)));
    }

    @PostMapping("/username")
    //@PreAuthorize("hasAnyAuthority('user_access')")
    public ResponseEntity<?> getUserByUsername(@RequestBody FindUserByUsername request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User", userService.getUserByUsername(request.getUsername())));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestBody(required = false) FilterUserDto filterUserDto, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User List", userService.getAllUsers(page, size, filterUserDto)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_Admin')")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User created", userService.create(signupRequest)));
    }

    @PostMapping("/dynamicParam")
    public ResponseEntity<?> listUserTestRecord(
            @RequestBody(required = false) UserFilterRequestDto userFilterRequestDto,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User retrieved",
                userService.userWithFirstName(page, size, userFilterRequestDto)));
    }
}
