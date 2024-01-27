package com.seyhavorn.springbootecommerce.authentication.mapper;

import com.seyhavorn.springbootecommerce.authentication.dto.SignupDto;
import com.seyhavorn.springbootecommerce.authentication.dto.UserDto;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public SignupDto fromUser(User user) {
        SignupDto signupDto = new SignupDto();
        BeanUtils.copyProperties(user, signupDto);
        return signupDto;
    }

    public User fromSignupDto(SignupDto signupDto) {
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
