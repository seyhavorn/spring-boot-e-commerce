package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CategoryResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Category;
import com.seyhavorn.springbootecommerce.shop.mapper.CategoryMapper;
import com.seyhavorn.springbootecommerce.shop.repository.CategoryRepository;
import com.seyhavorn.springbootecommerce.shop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResourceDto create(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.existsByName(categoryRequestDto.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = categoryMapper.categoryRequestDtoTocategory(categoryRequestDto);
        categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResourceDto(category);
    }

    @Override
    @Cacheable("categories")
    public Page<CategoryResourceDto> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageRequest);
        return new PageImpl<>(categories.getContent().stream()
                .map(categoryMapper::categoryToCategoryResourceDto)
                .collect(Collectors.toList()), pageRequest, categories.getTotalElements());
    }

    @Override
    public Category update(CategoryRequestDto categoryRequestDto, Long id) {
        return null;
    }

    @Override
    @Cacheable(value = "categories", key = "#id")
    public CategoryResourceDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        return categoryMapper.categoryToCategoryResourceDto(category);
    }

    @Override
    @CacheEvict(value = "categories", key = "#id")
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!"));
        categoryRepository.deleteById(id);
    }
}
