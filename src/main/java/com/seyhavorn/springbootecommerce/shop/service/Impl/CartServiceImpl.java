package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CartResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Cart;
import com.seyhavorn.springbootecommerce.shop.mapper.CartMapper;
import com.seyhavorn.springbootecommerce.shop.repository.CartRepository;
import com.seyhavorn.springbootecommerce.shop.service.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final FilterSpecificationService<Cart> filterSpecificationService;
    private final CartMapper cartMapper;

    @Override
    public CartResourceDto create(CartRequestDto cartRequestDto) {
        return null;
    }

    @Override
    public Page<CartResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public CartResourceDto update(CartRequestDto cartRequestDto, Long id) {
        return null;
    }

    @Override
    public CartResourceDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteBranch(Long id) {

    }
}
