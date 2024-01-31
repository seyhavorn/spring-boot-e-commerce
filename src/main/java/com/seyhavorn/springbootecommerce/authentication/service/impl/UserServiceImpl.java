package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyhavorn.springbootecommerce.authentication.dto.FilterUserDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.mapper.UserMapper;
import com.seyhavorn.springbootecommerce.authentication.repository.RoleRepository;
import com.seyhavorn.springbootecommerce.authentication.repository.UserRepository;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.dto.record.ListUserDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import com.seyhavorn.springbootecommerce.authentication.specifications.UserFilterSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsImpl createUser(SignupRequest signupDto) throws JsonProcessingException {
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = userMapper.fromSignupDto(signupDto);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setFirstName(signupDto.getFirst_name());
        user.setLastName(signupDto.getLast_name());
        user.setEmail(signupDto.getEmail());
        user.setUser_object(objectMapper.writeValueAsString(null));
        return new UserDetailsImpl(userRepository.save(user));
    }

    @Override
    public Boolean addRoleToUser(Long user_id, Long role_id) {
        try {
            User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
            Role role = roleRepository.findById(role_id).orElseThrow(() -> new RuntimeException("Role not found"));
            user.assignRoleToUser(role);
            userMapper.fromUserToDto(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean removeRoleFromUser(Long user_id, Long role_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(role_id).orElseThrow(() -> new RuntimeException("Role not found"));
        user.removeRoleFromUser(role);
        return true;
    }

    @Override
    public UserResource getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.fromUserToUserResource(user);
    }

    @Override
    public UserResource findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found!"));
        return userMapper.fromUserToUserResource(user);
    }

    @Override
    public Page<UserResource> getAllUsers(int page, int size, FilterUserDto filterUserDto) {
        Specification<User> specification = UserFilterSpecification.filter(filterUserDto);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(specification, pageRequest);
        return new PageImpl<>(users.getContent().stream().map(userMapper::fromUserToUserResource).toList(), pageRequest, users.getTotalElements());
    }

    @Override
    public UserResource create(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = userMapper.fromSignupDto(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFirstName(signupRequest.getFirst_name());
        user.setLastName(signupRequest.getLast_name());
        user.setEmail(signupRequest.getEmail());
        return userMapper.fromUserToUserResource(userRepository.save(user));
    }

    /*
        this method for list User with: record Dto:
     */
    @Override
    public List<ListUserDto> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(ListUserDto::fromUser).toList();
    }

}
