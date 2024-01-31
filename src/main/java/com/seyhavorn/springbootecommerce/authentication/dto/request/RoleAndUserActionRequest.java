package com.seyhavorn.springbootecommerce.authentication.dto.request;

import lombok.Data;

@Data
public class RoleAndUserActionRequest {

    private Long role_id;
    private Long user_id;
}
