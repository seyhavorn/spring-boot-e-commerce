package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CustomerResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResourceDto> findAll();

    Customer create(CustomerRequestDto customerRequestDto);

    CustomerResourceDto findById(Long id);

    Boolean deleteCustomer(Long id);
}
