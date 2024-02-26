package com.seyhavorn.springbootecommerce.order.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderItemsResourceDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderResourceDto;
import org.springframework.data.domain.Page;

public interface OrderItemsService {
    OrderItemsResourceDto create(OrderItemsRequestDto orderItemsRequestDto);

    Page<OrderItemsResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    OrderItemsResourceDto update(OrderResourceDto orderResourceDto);

    OrderItemsResourceDto findById(Long id);
}
