package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.shop.dto.request.CustomerRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CustomerResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Customer;
import com.seyhavorn.springbootecommerce.shop.mapper.CustomerMapper;
import com.seyhavorn.springbootecommerce.shop.repository.CustomRepository;
import com.seyhavorn.springbootecommerce.shop.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomRepository customRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Cacheable("customers")
    public Page<CustomerResourceDto> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customers = customRepository.findAll(pageRequest);
        return new PageImpl<>(customers.getContent().stream().map(customerMapper::customerToCustomerResources).collect(Collectors.toList()), pageRequest, customers.getTotalElements());
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public Customer create(CustomerRequestDto customerRequestDto) {
        try {
            if (customRepository.existsByUsername(customerRequestDto.getUsername())) {
                throw new RuntimeException("Customer already exists");
            }

            Customer customer = new Customer();
            customer.setName(customerRequestDto.getName());
            customer.setUsername(customerRequestDto.getUsername());
            customer.setEmail(customerRequestDto.getEmail());
            return customRepository.save(customer);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public Customer update(CustomerRequestDto customerRequestDto, Long id) {
        Customer customer = customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        BeanUtils.copyProperties(customerRequestDto, customer);

        if (customerRequestDto.getName() != null) {
            customerRequestDto.setName(customerRequestDto.getName());
        }

        if (customerRequestDto.getUsername() != null) {
            customerRequestDto.setName(customerRequestDto.getUsername());
        }

        if (customerRequestDto.getEmail() != null) {
            customerRequestDto.setName(customerRequestDto.getEmail());
        }

        /*
            This it my first time on this project to do another day with this. I really want to buy some clothes for good staff.
            I have do a lot of task for my career
         */
        customer = customRepository.save(customer);
        return customer;
    }

    @Override
    @Cacheable(value = "customers", key = "#id")
    public CustomerResourceDto findById(Long id) {
        Customer customer = customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        return customerMapper.customerToCustomerResources(customer);
    }

    @Override
    @CacheEvict(value = "customers", key = "#id", allEntries = false)
    public void deleteCustomer(Long id) {
        customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        customRepository.deleteById(id);
    }
}
