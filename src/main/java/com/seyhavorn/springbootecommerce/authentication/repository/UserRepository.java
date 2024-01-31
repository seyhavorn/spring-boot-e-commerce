package com.seyhavorn.springbootecommerce.authentication.repository;

import com.seyhavorn.springbootecommerce.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
