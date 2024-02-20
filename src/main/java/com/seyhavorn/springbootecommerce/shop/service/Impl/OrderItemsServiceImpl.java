package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.OrderItemsResourceDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.OrderResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.OrderItem;
import com.seyhavorn.springbootecommerce.shop.entity.Product;
import com.seyhavorn.springbootecommerce.shop.mapper.OrderItemsMapper;
import com.seyhavorn.springbootecommerce.shop.repository.OrderItemsRepository;
import com.seyhavorn.springbootecommerce.shop.repository.ProductRepository;
import com.seyhavorn.springbootecommerce.shop.service.OrderItemsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;
    private final FilterSpecificationService<OrderItem> filterSpecificationService;
    private final OrderItemsMapper orderItemsMapper;

    @Override
    public OrderItemsResourceDto create(OrderItemsRequestDto orderItemsRequestDto) {
        Product product = checkProduct(orderItemsRequestDto.getProduct_id());
        OrderItem orderItem = orderItemsMapper.orderItemsRequestDtoOrderItems(orderItemsRequestDto);
        orderItem.setProduct(product);
        orderItemsRepository.save(orderItem);
        return orderItemsMapper.orderItemsToOrderResourceDto(orderItem);
    }

    @Override
    public Page<OrderResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public OrderItemsResourceDto update(OrderResourceDto orderResourceDto) {
        return null;
    }

    @Override
    public OrderResourceDto findById(Long id) {
        return null;
    }

    private Product checkProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
    }
}
