package com.seyhavorn.springbootecommerce.authentication.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResource {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
