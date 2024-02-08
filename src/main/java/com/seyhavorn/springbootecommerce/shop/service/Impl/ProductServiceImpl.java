package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ProductResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Product;
import com.seyhavorn.springbootecommerce.shop.mapper.ProductMapper;
import com.seyhavorn.springbootecommerce.shop.repository.ProductRepository;
import com.seyhavorn.springbootecommerce.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final FilterSpecificationService<Product> filterSpecificationService;

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public ProductResourceDto create(ProductRequestDto productRequestDto) {
        if (productRepository.existsByName(productRequestDto.getName())) {
            throw new RuntimeException("Product already exists");
        }

        Product pr = productMapper.productRequestDtoToProduct(productRequestDto);
        Product product = productRepository.save(pr);
        return productMapper.productToProductResourceDto(product);
    }

    @Override
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
    public Product update(ProductRequestDto productRequestDto, Long id) {
        return null;
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
}
