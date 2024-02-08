package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ProductResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResourceDto create(ProductRequestDto productRequestDto);

    Page<ProductResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    Product update(ProductRequestDto productRequestDto, Long id);

    ProductResourceDto findById(Long id);

    void deleteProduct(Long id);
}
