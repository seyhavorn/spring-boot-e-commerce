package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource extends BaseResource{
    private Long id;
    private String name;
    private List<PermissionResource> permissions;
}
