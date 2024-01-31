package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResource extends BaseResource{
    private Long id;
    private String name;
}
