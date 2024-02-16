package com.seyhavorn.springbootecommerce.shop.dto.request;

import com.seyhavorn.springbootecommerce.helper.BaseRequest;
import lombok.Data;

import java.util.Date;

@Data
public class ShipmentRequestDto extends BaseRequest {
    private Date shipmentDate;

    private String address;

    private String city;

    private String state;

    private String country;
    private String zipCode;
}
