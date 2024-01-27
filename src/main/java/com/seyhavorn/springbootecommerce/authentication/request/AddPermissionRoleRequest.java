package com.seyhavorn.springbootecommerce.authentication.request;

import lombok.Data;

@Data
public class AddPermissionRoleRequest {
    private Long role_id;
    private Long permission_id;
}
