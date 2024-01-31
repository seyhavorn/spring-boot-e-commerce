package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResource extends BaseResource {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Object user_object;
    private List<RoleResource> roles;
}
