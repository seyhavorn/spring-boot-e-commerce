package com.seyhavorn.springbootecommerce.authentication.specifications;

import com.seyhavorn.springbootecommerce.authentication.dto.SearchRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.UserFilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecificationService<T> {
    public Specification<T> filterSpecification(SearchRequestDto searchRequestDto) {
        return (root, query, criteriaBuilder) -> {
            if (searchRequestDto != null) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<T> filterSpecification(List<SearchRequestDto> searchRequestDtoList, UserFilterRequestDto.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            if (searchRequestDtoList != null && globalOperator != null) {

                List<Predicate> predicates = new ArrayList<>();

                for (SearchRequestDto requestDto : searchRequestDtoList) {

                    switch (requestDto.getOperator()) {
                        case EQUAL -> {
                            Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                            predicates.add(equal);
                            break;
                        }

                        case LIKE -> {
                            Predicate like = criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get(requestDto.getColumn())), "%" + requestDto.getValue().toLowerCase() + "%");
                            predicates.add(like);
                            break;
                        }

                        case IN -> {
                            String[] split = requestDto.getValue().split(",");
                            Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
                            predicates.add(in);
                            break;
                        }

                        case GREATER_THAN -> {
                            Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
                            predicates.add(greaterThan);
                            break;
                        }

                        case LESS_THAN -> {
                            Predicate lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
                            predicates.add(lessThan);
                            break;
                        }

                        case BETWEEN -> {
                            String[] split1 = requestDto.getValue().split(",");
                            Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), Long.parseLong(split1[0]), Long.parseLong(split1[1]));
                            predicates.add(between);
                            break;
                        }

                        /*
                            case JOIN -> {
                                Predicate join = criteriaBuilder.equal(root.join(requestDto.getTable()).get(requestDto.getColumn()), requestDto.getValue());
                                predicates.add(join);
                                break;
                                ;
                            }
                         */

                        default -> throw new IllegalStateException("Unexpected value: " + " ");
                    }
                }

                if (globalOperator.equals(UserFilterRequestDto.GlobalOperator.AND)) {
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                } else {
                    return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
                }
            }

            return criteriaBuilder.conjunction();
        };
    }
}
