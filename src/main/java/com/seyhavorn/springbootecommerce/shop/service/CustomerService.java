package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CustomerResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Page<CustomerResourceDto> findAll(int page, int size);

    Customer create(CustomerRequestDto customerRequestDto);

    Customer update(CustomerRequestDto customerRequestDto, Long id);

    CustomerResourceDto findById(Long id);

    void deleteCustomer(Long id);
}
