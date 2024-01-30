package com.seyhavorn.springbootecommerce.authentication.repository;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(String roleName);

    Boolean existsByName(String rolesName);
}
