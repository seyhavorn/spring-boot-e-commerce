package com.seyhavorn.springbootecommerce.authentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seyhavorn.springbootecommerce.authentication.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.dto.UserDto;
import com.seyhavorn.springbootecommerce.authentication.service.impl.UserDetailsImpl;

public interface UserService {

    UserDetailsImpl createUser(SignupRequest signupDto) throws JsonProcessingException;

    UserDto addRoleToUser(Long user_id, Long role_id);

    Boolean removeRoleFromUser(Long user_id, Long role_id);

    UserDto getUserByUsername(String username);

}
