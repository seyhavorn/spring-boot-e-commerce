package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.OrderRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.OrderResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Order;
import com.seyhavorn.springbootecommerce.shop.mapper.OrderMapper;
import com.seyhavorn.springbootecommerce.shop.repository.OrderRepository;
import com.seyhavorn.springbootecommerce.shop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final FilterSpecificationService<Order> filterSpecificationService;
    private final OrderMapper orderMapper;

    @Override
    public OrderResourceDto create(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.orderRequestDtoOrder(orderRequestDto);
        orderRepository.save(order);
        return orderMapper.orderToOrderResourceDto(order);
    }

    @Override
    public Page<OrderResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public OrderResourceDto update(OrderResourceDto orderResourceDto) {
        return null;
    }

    @Override
    public OrderResourceDto findById(Long Id) {
        return null;
    }
}
