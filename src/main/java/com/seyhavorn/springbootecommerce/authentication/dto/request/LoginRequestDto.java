package com.seyhavorn.springbootecommerce.authentication.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
