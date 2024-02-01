package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResource extends BaseResource {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Object user_object;
    private List<RoleResource> roles;
}
