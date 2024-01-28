package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.dto.UserDto;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public SignupRequest fromUser(User user) {
        SignupRequest signupDto = new SignupRequest();
        BeanUtils.copyProperties(user, signupDto);
        return signupDto;
    }

    public User fromSignupDto(SignupRequest signupDto) {
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
}
