package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import com.seyhavorn.springbootecommerce.shop.dto.request.FetchUsersByShopId;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShopResourceDto;
import org.springframework.data.domain.Page;

public interface ShopService {
    ShopResourceDto create(ShopRequestDto shopRequestDto);

    Page<ShopResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    ShopResourceDto update(ShopRequestDto shopRequestDto);

    ShopResourceDto findById(Long id);

    void deleteShop(Long id);

    Boolean assignShopToUser(Long userId, Long shopId);

    Page<UserResource> fetchUsersByShopId(FetchUsersByShopId fetchUsersByShopId);

}
