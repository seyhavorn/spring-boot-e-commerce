package com.seyhavorn.springbootecommerce.order.mapper;

import com.seyhavorn.springbootecommerce.order.dto.request.OrderRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.Order;
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
