package com.seyhavorn.springbootecommerce.authentication.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private List<PermissionResource> permissions;
}
