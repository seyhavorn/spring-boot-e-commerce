package com.seyhavorn.springbootecommerce.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.seyhavorn.springbootecommerce.authentication.dto.SignupDto;
import com.seyhavorn.springbootecommerce.authentication.service.PermissionService;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final PermissionService permissionService;
    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        createPermission();
        createRole();
        createUser();
    }

    private void createPermission() {
        Arrays.asList("user_access", "user_create", "user_update", "user_delete", "shop_access", "shop_create", "shop_update", "shop_delete").forEach(permissionService::addPermission);
    }

    protected void createRole() {
        Arrays.asList("Admin", "User", "Staff", "Employee").forEach(roleService::addRole);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            roleService.addPermissionToRole(random.nextLong(4) + 1, random.nextLong(8) + 1);
        }
    }

    private void createUser() throws JsonProcessingException {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            SignupDto signupDto = new SignupDto();
            signupDto.setUsername("test" + i);
            signupDto.setPassword("password");
            signupDto.setFirst_name(faker.name().firstName());
            signupDto.setLast_name(faker.name().lastName());
            userService.createUser(signupDto);
            Random random = new Random();
            userService.addRoleToUser((long) i, random.nextLong(4));
        }
    }
}
