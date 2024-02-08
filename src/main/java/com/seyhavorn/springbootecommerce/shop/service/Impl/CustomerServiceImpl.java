package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomRepository customRepository;
    private final CustomerMapper customerMapper;
    private final FilterSpecificationService<Customer> filterSpecificationService;

    @Override
    @Cacheable("customers")
    public Page<CustomerResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customers;

        if (filterRequestDto != null) {
            Specification<Customer> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(), filterRequestDto.getGlobalOperator());
            customers = customRepository.findAll(specification, pageRequest);
        } else {
            customers = customRepository.findAll(pageRequest);
        }

        return new PageImpl<>(customers.getContent()
                .stream()
                .map(customerMapper::customerToCustomerResources)
                .toList(),
                pageRequest, customers.getTotalElements());
    }

    @Override
    @CacheEvict(value = "customers", allEntries = true)
    public CustomerResourceDto create(CustomerRequestDto customerRequestDto) {
        try {
            if (customRepository.existsByUsername(customerRequestDto.getUsername())) {
                throw new RuntimeException("Customer already exists");
            }
            Customer customer = customerMapper.customerToCustomerRequestDto(customerRequestDto);
            Customer c = customRepository.save(customer);
            return customerMapper.customerToCustomerResources(c);
        } catch (Exception ignored) {

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
    @CacheEvict(value = "customers", key = "#id")
    public void deleteCustomer(Long id) {
        customRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
        customRepository.deleteById(id);
    }
}
