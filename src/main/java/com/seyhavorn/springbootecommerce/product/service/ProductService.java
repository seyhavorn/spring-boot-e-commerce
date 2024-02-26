package com.seyhavorn.springbootecommerce.product.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.product.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.product.dto.resource.ProductResourceDto;
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
