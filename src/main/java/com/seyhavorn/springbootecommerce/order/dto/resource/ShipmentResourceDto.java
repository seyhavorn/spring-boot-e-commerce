package com.seyhavorn.springbootecommerce.order.dto.resource;

import com.seyhavorn.springbootecommerce.helper.BaseResource;
import lombok.Data;

import java.util.Date;

@Data
public class ShipmentResourceDto extends BaseResource {
    private Date shipmentDate;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
