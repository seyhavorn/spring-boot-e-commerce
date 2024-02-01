package com.seyhavorn.springbootecommerce.authentication.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserFilterRequestDto {
    private List<SearchRequestDto> searchRequestDtoList;
    private GlobalOperator globalOperator;

    public enum GlobalOperator {
        AND, OR
    }

}

