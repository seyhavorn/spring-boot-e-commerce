package com.seyhavorn.springbootecommerce.authentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.service.impl.UserDetailsImpl;

import java.util.List;

public interface UserService {
    UserDetailsImpl createUser(SignupRequest signupDto) throws JsonProcessingException;

    Boolean addRoleToUser(Long user_id, Long role_id);

    Boolean removeRoleFromUser(Long user_id, Long role_id);

    User getUserByUsername(String username);

    List<UserResource> findAll();

    UserResource findUserById(Long userId);
}
