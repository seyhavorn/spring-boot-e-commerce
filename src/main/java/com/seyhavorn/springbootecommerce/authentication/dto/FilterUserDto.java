package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterUserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String createdAt;
    private String updatedAt;
}
