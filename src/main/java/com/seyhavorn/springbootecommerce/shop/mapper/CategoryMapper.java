package com.seyhavorn.springbootecommerce.shop.mapper;


import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CategoryResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category categoryRequestDtoTocategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequestDto, category);
        return category;
    }

    public CategoryResourceDto categoryToCategoryResourceDto(Category category) {
        CategoryResourceDto categoryResourceDto = new CategoryResourceDto();
        BeanUtils.copyProperties(category, categoryResourceDto);
        return categoryResourceDto;
    }
}
