package com.seyhavorn.springbootecommerce.authentication.dto;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String user_id;
    private String accessToken;
    private String refreshToken;
    private RoleDto role;

    public void setRole(Role role) {
        this.role = new RoleDto(role.getId(), role.getName(),
                role.getPermissions().stream().map(
                        permission -> new PermissionDto(permission.getId(), permission.getName())
                ).toList()
        );

//        this.role.setId(role.getId());
//        this.role.setName(role.getName());
//        this.role.setPermissions(role.getPermissions().stream().map(permission -> {
//            PermissionDto permissionDto = new PermissionDto();
//            permissionDto.setId(permission.getId());
//            permissionDto.setName(permission.getName());
//            return permissionDto;
//        }).toList());

    }
}
