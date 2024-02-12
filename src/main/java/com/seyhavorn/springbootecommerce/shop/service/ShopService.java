package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShopResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import org.springframework.data.domain.Page;

public interface ShopService {
    ShopResourceDto create(ShopRequestDto shopRequestDto);

    Page<ShopResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    Shop update(ShopRequestDto shopRequestDto, Long id);

    ShopResourceDto findById(Long id);

    void deleteShop(Long id);
}
