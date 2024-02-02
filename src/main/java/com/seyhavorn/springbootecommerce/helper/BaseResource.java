package com.seyhavorn.springbootecommerce.helper;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseResource implements Serializable {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
