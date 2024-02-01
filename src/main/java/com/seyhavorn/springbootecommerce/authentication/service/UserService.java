package com.seyhavorn.springbootecommerce.authentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seyhavorn.springbootecommerce.authentication.dto.FilterUserDto;
import com.seyhavorn.springbootecommerce.authentication.dto.UserFilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.record.ListUserDto;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.service.impl.UserDetailsImpl;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDetailsImpl createUser(SignupRequest signupDto) throws JsonProcessingException;

    Boolean addRoleToUser(Long user_id, Long role_id);

    Boolean removeRoleFromUser(Long user_id, Long role_id);

    UserResource getUserByUsername(String username);

    UserResource findUserById(Long userId);

    Page<UserResource> getAllUsers(int page, int size, FilterUserDto filterUserDto);

    UserResource create(SignupRequest signupRequest);

    List<ListUserDto> listUsers();

    Page<UserResource> userWithFirstName(int page, int size,UserFilterRequestDto userFilterRequestDto);
}
