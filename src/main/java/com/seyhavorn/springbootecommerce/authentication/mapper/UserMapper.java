package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyhavorn.springbootecommerce.authentication.dto.UserDto;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.RoleResource;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserMapper {
    private final ObjectMapper objectMapper;

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

    public UserResource fromUserToUserResource(User user){
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
        Map userObject = null;
        try {
            userObject = objectMapper.readValue(user.getUser_object(), Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        userResource.setUser_object(userObject);
        return userResource;
    }
}
