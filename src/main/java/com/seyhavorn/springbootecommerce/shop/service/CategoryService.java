package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CategoryResourceDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryResourceDto create(CategoryRequestDto categoryRequestDto);

    Page<CategoryResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    CategoryResourceDto update(CategoryRequestDto categoryRequestDto, Long id);

    CategoryResourceDto findById(Long id);

    void deleteCategory(Long id);
}
