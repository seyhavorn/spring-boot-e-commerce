package com.seyhavorn.springbootecommerce.authentication.dto.request;

import lombok.Data;

@Data
public class RoleAndUserActionRequestDto {

    private Long role_id;
    private Long user_id;
}
