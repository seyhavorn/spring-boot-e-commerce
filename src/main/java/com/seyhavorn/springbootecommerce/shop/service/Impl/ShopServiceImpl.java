package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShopResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import com.seyhavorn.springbootecommerce.shop.mapper.ShopMapper;
import com.seyhavorn.springbootecommerce.shop.repository.ShopRepository;
import com.seyhavorn.springbootecommerce.shop.service.ShopService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final FilterSpecificationService<Shop> filterSpecificationService;
    private final ShopMapper shopMapper;


    @Override
    public ShopResourceDto create(ShopRequestDto shopRequestDto) {
        return null;
    }

    @Override
    public Page<ShopResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        Page<Shop> shops;
        PageRequest pageRequest = PageRequest.of(page, size);

        if (filterRequestDto != null) {
            Specification<Shop> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(),
                    filterRequestDto.getGlobalOperator()
            );
            shops = shopRepository.findAll(specification, pageRequest);
        } else {
            shops = shopRepository.findAll(pageRequest);
        }

        return new PageImpl<>(shops.getContent().stream()
                .map(shopMapper::shopToShopResourceDto)
                .toList(), pageRequest, shops.getTotalElements()
        );
    }

    @Override
    public Shop update(ShopRequestDto shopRequestDto, Long id) {
        return null;
    }

    @Override
    public ShopResourceDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteShop(Long id) {

    }
}
