package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.dto.UserDto;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.RoleResource;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {

    public SignupRequestDto fromUser(User user) {
        SignupRequestDto signupDto = new SignupRequestDto();
        BeanUtils.copyProperties(user, signupDto);
        return signupDto;
    }

    public User fromSignupDto(SignupRequestDto signupDto) {
        User user = new User();
        BeanUtils.copyProperties(signupDto, user);
        return user;
    }

    public UserDto fromUserToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public User fromDtoToUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public UserResource fromUserToUserResource(User user) {
        UserResource userResource = new UserResource();
        BeanUtils.copyProperties(user, userResource);

        //roles list:
        List<RoleResource> roles = user.getRoles()
                .stream()
                .map(role -> {
                    RoleResource roleResource = new RoleResource();
                    BeanUtils.copyProperties(role, roleResource);
                    return roleResource;
                }).toList();

        userResource.setRoles(roles);
        return userResource;
    }
}
