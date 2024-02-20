package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.CategoryRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CategoryResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Category;
import com.seyhavorn.springbootecommerce.shop.mapper.CategoryMapper;
import com.seyhavorn.springbootecommerce.shop.repository.CategoryRepository;
import com.seyhavorn.springbootecommerce.shop.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final FilterSpecificationService<Category> filterSpecificationService;


    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResourceDto create(CategoryRequestDto categoryRequestDto) {
        try {
            if (categoryRepository.existsByName(categoryRequestDto.getName())) {
                throw new RuntimeException("Category already exists");
            }

            Category category = categoryMapper.categoryRequestDtoTocategory(categoryRequestDto);
            categoryRepository.save(category);
            return categoryMapper.categoryToCategoryResourceDto(category);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    @Cacheable("categories")
    public Page<CategoryResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        Page<Category> categories;
        PageRequest pageRequest = PageRequest.of(page, size);

        if (filterRequestDto != null) {
            Specification<Category> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(),
                    filterRequestDto.getGlobalOperator()
            );
            categories = categoryRepository.findAll(specification, pageRequest);
        } else {
            categories = categoryRepository.findAll(pageRequest);
        }

        return new PageImpl<>(categories.getContent().stream()
                .map(categoryMapper::categoryToCategoryResourceDto)
                .toList(), pageRequest, categories.getTotalElements());
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResourceDto update(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = checkCategoryExists(id);

        if (categoryRepository.existsByName(categoryRequestDto.getName())) {
            throw new RuntimeException("Category already exists");
        }

        BeanUtils.copyProperties(categoryRequestDto, category);
        categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResourceDto(category);
    }

    @Override
    @Cacheable(value = "categories", key = "#id")
    public CategoryResourceDto findById(Long id) {
        Category category = checkCategoryExists(id);
        return categoryMapper.categoryToCategoryResourceDto(category);
    }

    @Override
    @CacheEvict(value = "categories", key = "#id")
    public void deleteCategory(Long id) {
        Category category = checkCategoryExists(id);
        category.getProducts().forEach(product -> product.setCategory(null));
        categoryRepository.deleteById(id);
    }

    private Category checkCategoryExists(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
