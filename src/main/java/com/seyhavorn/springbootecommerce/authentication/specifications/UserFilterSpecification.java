package com.seyhavorn.springbootecommerce.authentication.specifications;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterUserDto;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFilterSpecification {
    public static Specification<User> filter(FilterUserDto filterUserDto) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filterUserDto != null) {
                if (filterUserDto.getId() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), filterUserDto.getId()));
                }

                if (StringUtils.isNotEmpty(filterUserDto.getUsername())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("username"), filterUserDto.getUsername()));
                }
            }

            return predicate;
        });
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
