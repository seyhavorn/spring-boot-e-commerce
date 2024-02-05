package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CustomerResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerResourceDto customerToCustomerResources(Customer customer) {
        CustomerResourceDto customerResourceDto = new CustomerResourceDto();
        BeanUtils.copyProperties(customer, customerResourceDto);
        return customerResourceDto;
    }

    public Customer customerToCustomerRequestDto(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer);
        return customer;
    }
}
