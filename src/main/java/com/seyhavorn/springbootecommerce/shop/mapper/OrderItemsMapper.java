package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.OrderItemsResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.OrderItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsMapper {
    public OrderItemsResourceDto orderItemsToOrderResourceDto(OrderItem orderItem) {
        OrderItemsResourceDto orderItemsResourceDto = new OrderItemsResourceDto();

        if (orderItem.getProduct() != null && orderItem.getProduct().getId() != null) {
            orderItemsResourceDto.setProduct_id(orderItem.getProduct().getId());
        }

        BeanUtils.copyProperties(orderItem, orderItemsResourceDto);
        return orderItemsResourceDto;
    }

    public OrderItem orderItemsRequestDtoOrderItems(OrderItemsRequestDto orderItemsRequestDto) {
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemsRequestDto, orderItem);
        return orderItem;
    }
}
