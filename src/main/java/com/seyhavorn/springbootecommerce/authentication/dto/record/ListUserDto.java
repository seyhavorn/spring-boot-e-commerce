package com.seyhavorn.springbootecommerce.authentication.dto.record;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.entity.User;

import java.util.Set;

public record ListUserDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        Set<Role> roles
) {

    public static ListUserDto fromUser(User user) {
        return new ListUserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
