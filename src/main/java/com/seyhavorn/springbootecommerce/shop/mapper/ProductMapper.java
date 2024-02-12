package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.ProductRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ProductResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResourceDto productToProductResourceDto(Product product) {
        ProductResourceDto productResourceDto = new ProductResourceDto();
        BeanUtils.copyProperties(product, productResourceDto);

        if (product.getCategory() != null && product.getCategory().getId() != null) {
            productResourceDto.setCategory_id(product.getCategory().getId());
        }

        return productResourceDto;
    }

    public Product productRequestDtoToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequestDto, product);
        return product;
    }
}
