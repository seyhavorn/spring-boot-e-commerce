package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionResource extends BaseResource {
    private String name;
}
