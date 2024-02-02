package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleResource extends BaseResource {
    private String name;
    private List<PermissionResource> permissions;
}
