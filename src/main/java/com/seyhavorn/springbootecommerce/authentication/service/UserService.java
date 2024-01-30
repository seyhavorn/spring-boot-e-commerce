package com.seyhavorn.springbootecommerce.authentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seyhavorn.springbootecommerce.authentication.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.service.impl.UserDetailsImpl;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDetailsImpl createUser(SignupRequest signupDto) throws JsonProcessingException;

    Boolean addRoleToUser(Long user_id, Long role_id);

    Boolean removeRoleFromUser(Long user_id, Long role_id);

    UserResource getUserByUsername(String username);

    UserResource findUserById(Long userId);
    Page<UserResource> getAllUsers(int page, int size);
    UserResource create(SignupRequest signupRequest);
}
