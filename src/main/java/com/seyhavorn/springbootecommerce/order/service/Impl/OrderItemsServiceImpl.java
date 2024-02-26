package com.seyhavorn.springbootecommerce.order.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.order.dto.request.OrderItemsRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderItemsResourceDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.OrderResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.OrderItem;
import com.seyhavorn.springbootecommerce.product.entity.Product;
import com.seyhavorn.springbootecommerce.order.mapper.OrderItemsMapper;
import com.seyhavorn.springbootecommerce.order.repository.OrderItemsRepository;
import com.seyhavorn.springbootecommerce.product.repository.ProductRepository;
import com.seyhavorn.springbootecommerce.order.service.OrderItemsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    @CacheEvict(value = "orderItems", allEntries = true)
    public OrderItemsResourceDto create(OrderItemsRequestDto orderItemsRequestDto) {
        Product product = checkProduct(orderItemsRequestDto.getProduct_id());
        OrderItem orderItem = orderItemsMapper.orderItemsRequestDtoOrderItems(orderItemsRequestDto);
        orderItem.setProduct(product);
        orderItemsRepository.save(orderItem);
        return orderItemsMapper.orderItemsToOrderResourceDto(orderItem);
    }

    @Override
    @Cacheable("orderItems")
    public Page<OrderItemsResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        Page<OrderItem> orderItems;
        PageRequest pageRequest = PageRequest.of(page, size);

        if (filterRequestDto != null) {
            Specification<OrderItem> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(),
                    filterRequestDto.getGlobalOperator()
            );
            orderItems = orderItemsRepository.findAll(specification, pageRequest);
        } else {
            orderItems = orderItemsRepository.findAll(pageRequest);
        }

        return new PageImpl<>(orderItems.getContent().stream()
                .map(orderItemsMapper::orderItemsToOrderResourceDto)
                .toList(), pageRequest, orderItems.getTotalElements());
    }


    @Override
    public OrderItemsResourceDto update(OrderResourceDto orderResourceDto) {
        return null;
    }

    @Override
    public OrderItemsResourceDto findById(Long id) {
        return null;
    }

    private Product checkProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
    }
}
