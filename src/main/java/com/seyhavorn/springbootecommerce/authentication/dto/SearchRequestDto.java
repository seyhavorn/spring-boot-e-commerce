package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.Data;

@Data
public class SearchRequestDto {
    private String column;
    private String value;

    private Operator operator;

    public enum Operator {
        LIKE, EQUAL, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN
    }
}
