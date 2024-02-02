package com.seyhavorn.springbootecommerce.authentication.dto.request;

import lombok.Data;

@Data
public class AddPermissionRoleRequestDto {
    private Long role_id;
    private Long permission_id;
}
