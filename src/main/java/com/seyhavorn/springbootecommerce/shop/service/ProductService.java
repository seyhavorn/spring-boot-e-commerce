package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ProductResourceDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResourceDto create(ProductRequestDto productRequestDto);

    Page<ProductResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    ProductResourceDto update(ProductRequestDto productRequestDto, Long id);

    ProductResourceDto findById(Long id);

    void deleteProduct(Long id);

    List<ProductResourceDto> getProductsByCategoryId(Long categoryId);
}
