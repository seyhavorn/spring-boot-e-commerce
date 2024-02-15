package com.seyhavorn.springbootecommerce.helper;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@Data
public class BaseRequest {
    private Date createdAt;

    private Date updatedAt;

    @CreatedBy
    private String created_by;

    @LastModifiedBy
    private String updated_by;
}
