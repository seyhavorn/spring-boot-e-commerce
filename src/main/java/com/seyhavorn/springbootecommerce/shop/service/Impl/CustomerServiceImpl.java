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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomRepository customRepository;
    private final CustomerMapper customerMapper;
    private final CacheManager cacheManager;

    @Override
    @Cacheable(value = "customers")
    public List<CustomerResourceDto> findAll() {
        List<Customer> customers = customRepository.findAll();
        return customers.stream().map(customerMapper::customerToCustomerResources).toList();
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
    @Cacheable(value = "customers", key = "#id")
    public CustomerResourceDto findById(Long id) {
        Customer customer = customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        return customerMapper.customerToCustomerResources(customer);
    }

    @Override
    @CacheEvict(value = "customers", key = "#id")
    public Boolean deleteCustomer(Long id) {
        customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        Cache cache = cacheManager.getCache("customers");
        if (cache != null) {
            cache.evict(id);
        }
        customRepository.deleteById(id);
        return null;
    }
}
