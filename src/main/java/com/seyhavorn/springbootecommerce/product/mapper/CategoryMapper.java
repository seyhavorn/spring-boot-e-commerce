package com.seyhavorn.springbootecommerce.product.mapper;


import com.seyhavorn.springbootecommerce.product.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.product.dto.resource.CategoryResourceDto;
import com.seyhavorn.springbootecommerce.product.entity.Category;
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
