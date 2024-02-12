package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ProductResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Category;
import com.seyhavorn.springbootecommerce.shop.entity.Product;
import com.seyhavorn.springbootecommerce.shop.mapper.ProductMapper;
import com.seyhavorn.springbootecommerce.shop.repository.CategoryRepository;
import com.seyhavorn.springbootecommerce.shop.repository.ProductRepository;
import com.seyhavorn.springbootecommerce.shop.service.ProductService;
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

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final FilterSpecificationService<Product> filterSpecificationService;

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductResourceDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(
                productRequestDto.getCategory_id()).orElseThrow(() -> new RuntimeException("Category not found!"));

        Product product = productMapper.productRequestDtoToProduct(productRequestDto);
        product.setCategory(category);
        Product product1 = productRepository.save(product);
        System.out.println("product 1" + product1);
        return productMapper.productToProductResourceDto(product1);
    }

    @Override
    @Cacheable(value = "products")
    public Page<ProductResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products;

        if (filterRequestDto != null) {
            Specification<Product> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(), filterRequestDto.getGlobalOperator()
            );
            products = productRepository.findAll(specification, pageRequest);
        } else {
            products = productRepository.findAll(pageRequest);
        }

        return new PageImpl<>(products.getContent()
                .stream()
                .map(productMapper::productToProductResourceDto).toList(),
                pageRequest, products.getTotalElements());
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public Product update(ProductRequestDto productRequestDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        BeanUtils.copyProperties(product, productRequestDto);
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductResourceDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        return productMapper.productToProductResourceDto(product);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        productRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "products")
    public List<ProductResourceDto> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found!"));
        List<Product> products = productRepository.getProductByCategoryId(category.getId());
        return products.stream().map(productMapper::productToProductResourceDto).toList();
    }
}
