package com.seyhavorn.springbootecommerce.authentication.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResource extends BaseResource{
    private Long id;
    private String name;
}
