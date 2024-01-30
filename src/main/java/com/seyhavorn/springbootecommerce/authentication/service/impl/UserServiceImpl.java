package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.mapper.UserMapper;
import com.seyhavorn.springbootecommerce.authentication.repository.RoleRepository;
import com.seyhavorn.springbootecommerce.authentication.repository.UserRepository;
import com.seyhavorn.springbootecommerce.authentication.request.SignupRequest;
import com.seyhavorn.springbootecommerce.authentication.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
        user.setUser_object(objectMapper.writeValueAsString(signupDto));
        user.setFirstName(signupDto.getFirst_name());
        user.setLastName(signupDto.getLast_name());
        user.setEmail(signupDto.getEmail());
        return new UserDetailsImpl(userRepository.save(user));
    }

    @Override
    public Boolean addRoleToUser(Long user_id, Long role_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(role_id).orElseThrow(() -> new RuntimeException("Role not found"));
        user.assignRoleToUser(role);
        userMapper.fromUserToDto(user);
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
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResource> findAll() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
        return users.stream().map(userMapper::fromUserToUserResource).toList();
    }

    @Override
    public UserResource findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found!"));
        return userMapper.fromUserToUserResource(user);
    }
}
