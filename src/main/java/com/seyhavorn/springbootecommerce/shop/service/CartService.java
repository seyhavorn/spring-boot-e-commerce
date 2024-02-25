package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CartResourceDto;
import org.springframework.data.domain.Page;

public interface CartService {
    CartResourceDto create(CartRequestDto cartRequestDto);

    Page<CartResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    CartResourceDto update(CartRequestDto cartRequestDto, Long id);

    CartResourceDto findById(Long id);

    void deleteBranch(Long id);
}
