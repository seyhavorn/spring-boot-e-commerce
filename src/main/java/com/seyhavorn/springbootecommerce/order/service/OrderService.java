package com.seyhavorn.springbootecommerce.order.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.request.OrderRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderResourceDto;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResourceDto create(OrderRequestDto orderRequestDto);

    Page<OrderResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    OrderResourceDto update(OrderResourceDto orderResourceDto);

    OrderResourceDto findById(Long Id);
}
