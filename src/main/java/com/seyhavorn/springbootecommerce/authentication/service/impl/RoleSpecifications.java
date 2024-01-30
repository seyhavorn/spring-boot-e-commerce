package com.seyhavorn.springbootecommerce.authentication.service.impl;

import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecifications {
    public static Specification<Role> nameContain(String name) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(name)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
            return criteriaBuilder.conjunction();
        };
    }
}
