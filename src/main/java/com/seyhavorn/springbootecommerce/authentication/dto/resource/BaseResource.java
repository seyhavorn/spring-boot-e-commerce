package com.seyhavorn.springbootecommerce.authentication.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResource {
    private Date createdAt;
    private Date updatedAt;
}
