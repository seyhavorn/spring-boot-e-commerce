package com.seyhavorn.springbootecommerce.authentication.specifications;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRoleDto;
import com.seyhavorn.springbootecommerce.authentication.entity.Role;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RoleFilterSpecifications {

    public static Specification<Role> filter(FilterRoleDto filterRoleDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filterRoleDto != null) {
                if (StringUtils.isNotEmpty(filterRoleDto.getName())) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filterRoleDto.getName().toLowerCase() + "%")
                    );
                }

                if (StringUtils.isNotEmpty(filterRoleDto.getCreatedAt())) {
                    predicate = criteriaBuilder.or(
                            predicate,
                            criteriaBuilder.equal(root.get("createdAt"), parseDateTime(filterRoleDto.getCreatedAt()))
                    );
                }

                if (StringUtils.isNotEmpty(filterRoleDto.getUpdatedAt())) {
                    predicate = criteriaBuilder.or(
                            predicate,
                            criteriaBuilder.equal(root.get("updatedAt"), parseDateTime(filterRoleDto.getUpdatedAt()))
                    );
                }

                if (filterRoleDto.getId() != null) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(root.get("id"), filterRoleDto.getId())
                    );
                }

                if (StringUtils.isNotEmpty(filterRoleDto.getUpdatedAt())) {
                    query.orderBy(criteriaBuilder.desc(root.get("updatedAt")));
                }
            }

            return predicate;
        };
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }


 /*
    public static Specification<Role> filter(FilterRoleDto filterRoleDto) {
        return (root, query, criteriaBuilder) -> {
            if (filterRoleDto != null) {
                return criteriaBuilder.and(
                        StringUtils.isEmpty(filterRoleDto.getName()) ? criteriaBuilder.conjunction() :
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filterRoleDto.getName().toLowerCase() + "%"),


                        StringUtils.isEmpty(filterRoleDto.getCreatedAt()) ? criteriaBuilder.conjunction() :
                                criteriaBuilder.equal(root.get("createdAt"), parseDateTime(filterRoleDto.getCreatedAt())),


                        StringUtils.isEmpty(filterRoleDto.getUpdatedAt()) ? criteriaBuilder.conjunction() :
                                criteriaBuilder.equal(root.get("updatedAt"), parseDateTime(filterRoleDto.getUpdatedAt())),

                        // Add more conditions based on other properties of FilterRoleDto
                        StringUtils.isEmpty(filterRoleDto.getId()) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("id"), filterRoleDto.getId())
                );
            }
            // If no parameters are provided, return all roles
            return criteriaBuilder.conjunction();
        };
    }

  */

}
