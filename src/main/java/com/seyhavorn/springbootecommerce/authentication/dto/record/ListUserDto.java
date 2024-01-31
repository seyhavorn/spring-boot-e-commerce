package com.seyhavorn.springbootecommerce.authentication.dto.record;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.entity.User;

import java.util.Set;

public record ListUserDto(
        String username,
        String firstName,
        String lastName,
        Set<Role> roles
) {

    public static ListUserDto fromUser(User user) {
        return new ListUserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getRoles());
    }
}
