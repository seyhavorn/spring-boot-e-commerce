package com.seyhavorn.springbootecommerce.authentication.dto;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private List<Role> roles;
}
