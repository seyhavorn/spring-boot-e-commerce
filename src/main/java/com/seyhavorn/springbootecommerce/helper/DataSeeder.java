package com.seyhavorn.springbootecommerce.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.seyhavorn.springbootecommerce.authentication.dto.request.SignupRequestDto;
import com.seyhavorn.springbootecommerce.authentication.service.PermissionService;
import com.seyhavorn.springbootecommerce.authentication.service.RoleService;
import com.seyhavorn.springbootecommerce.authentication.service.UserService;
import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CategoryResourceDto;
import com.seyhavorn.springbootecommerce.shop.service.CategoryService;
import com.seyhavorn.springbootecommerce.shop.service.CustomerService;
import com.seyhavorn.springbootecommerce.shop.service.ProductService;
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
    private final CustomerService customerService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
//        createPermission();
//        createRole();
//        createUser();
//        crateCustomer();
//        createCategories();
    }

//    private void createProducts() {
//        Faker faker = new Faker();
//        for (int i = 0; i < 10; i++) {
//            ProductRequestDto productRequestDto = new ProductRequestDto();
//            productRequestDto.setName(faker.name().title());
//            productRequestDto.setCategory_id((long) faker.number().numberBetween(1, 10));
//            productRequestDto.setDescription(faker.lorem().sentence());
//            productRequestDto.setPrice(faker.number().randomDigit());
//            productRequestDto.setDiscount(faker.number().randomDigit());
//            productService.create(productRequestDto);
//        }
//    }

    private void createCategories() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
            categoryRequestDto.setName(faker.name().fullName());
            categoryRequestDto.setDescription(faker.name().title());
            categoryRequestDto.setImageUrl(faker.internet().image());
            CategoryResourceDto categoryResourceDto = categoryService.create(categoryRequestDto);

            for (int j = 0; j < 2; j++) {
                ProductRequestDto productRequestDto = new ProductRequestDto();
                productRequestDto.setName(faker.name().title());
                productRequestDto.setCategory_id(categoryResourceDto.getId());
                productRequestDto.setDescription(faker.lorem().sentence());
                productRequestDto.setPrice(faker.number().randomDigit());
                productRequestDto.setDiscount(faker.number().randomDigit());
                productService.create(productRequestDto);
            }

        }
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
        for (int i = 0; i < 30; i++) {
            SignupRequestDto signupDto = new SignupRequestDto();
            signupDto.setUsername("test" + i);
            signupDto.setPassword("password");
            signupDto.setFirst_name(faker.name().firstName());
            signupDto.setLast_name(faker.name().lastName());
            signupDto.setEmail(faker.internet().emailAddress());
            userService.createUser(signupDto);
            Random random = new Random();
            userService.addRoleToUser((long) i, random.nextLong(4));
        }
    }

    private void crateCustomer() {
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            CustomerRequestDto customerRequestDto = new CustomerRequestDto();
            customerRequestDto.setName(faker.name().fullName());
            customerRequestDto.setUsername(faker.name().username());
            customerRequestDto.setEmail(faker.internet().emailAddress());
            customerService.create(customerRequestDto);
        }
    }
}
