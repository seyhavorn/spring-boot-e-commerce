package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.OrderRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.OrderResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderResourceDto orderToOrderResourceDto(Order order) {
        OrderResourceDto orderResourceDto = new OrderResourceDto();
        BeanUtils.copyProperties(order, orderResourceDto);
        return orderResourceDto;
    }

    public Order orderRequestDtoOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequestDto, order);
        return order;
    }
}
