package com.seyhavorn.springbootecommerce.order.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.CartResourceDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {
    CartResourceDto create(CartRequestDto cartRequestDto);

    Page<CartResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    CartResourceDto update(CartRequestDto cartRequestDto, Long id);

    CartResourceDto findById(Long id);

    void deleteBranch(Long id);

    List<CartResourceDto> findCartByCustomerId(Long customerId);
}
