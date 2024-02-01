package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResource {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
