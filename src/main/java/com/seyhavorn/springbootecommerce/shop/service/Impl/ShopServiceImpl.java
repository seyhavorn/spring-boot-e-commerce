package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.dto.resource.UserResource;
import com.seyhavorn.springbootecommerce.authentication.entity.User;
import com.seyhavorn.springbootecommerce.authentication.mapper.UserMapper;
import com.seyhavorn.springbootecommerce.authentication.repository.UserRepository;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.FetchUsersByShopId;
import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShopResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import com.seyhavorn.springbootecommerce.shop.mapper.ShopMapper;
import com.seyhavorn.springbootecommerce.shop.repository.ShopRepository;
import com.seyhavorn.springbootecommerce.shop.service.ShopService;
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
@Transactional
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final FilterSpecificationService<Shop> filterSpecificationService;
    private final ShopMapper shopMapper;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @CacheEvict(value = "shop", allEntries = true)
    public ShopResourceDto create(ShopRequestDto shopRequestDto) {
        if (shopRepository.existsByName(shopRequestDto.getName())) {
            throw new RuntimeException("shop already exists");
        }

        Shop shop = shopMapper.shopRequestDtoToShop(shopRequestDto);
        shopRepository.save(shop);
        return shopMapper.shopToShopResourceDto(shop);
    }

    @Override
    @Cacheable(value = "shop")
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
    @Cacheable(value = "shop")
    public ShopResourceDto update(ShopRequestDto shopRequestDto) {
        Shop shop = checkShop(shopRequestDto.getId());
        BeanUtils.copyProperties(shopRequestDto, shop);
        shopRepository.save(shop);
        return shopMapper.shopToShopResourceDto(shop);
    }

    @Override
    @Cacheable(value = "shop", key = "#id")
    public ShopResourceDto findById(Long id) {
        Shop shop = checkShop(id);
        return shopMapper.shopToShopResourceDto(shop);
    }

    @Override
    @CacheEvict(value = "shop", key = "#id")
    public void deleteShop(Long id) {
        Shop shop = checkShop(id);
        shop.getBranches().forEach(branch -> branch.setShop(null));
        shopRepository.deleteById(id);
    }

    @Override
    public Boolean assignShopToUser(Long userId, Long shopId) {
        Shop shop = checkShop(shopId);
        User user = checkUser(userId);
        try {
            shop.assignUserToShop(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Page<UserResource> fetchUsersByShopId(FetchUsersByShopId fetchUsersByShopId) {
        Shop shop = checkShop(fetchUsersByShopId.getShopId());
        PageRequest pageRequest = PageRequest.of(fetchUsersByShopId.getPage(), fetchUsersByShopId.getSize());

        Page<User> users = userRepository.findUserByShopId(shop.getId(), pageRequest);

        List<UserResource> userResources = users.getContent().stream()
                .map(userMapper::fromUserToUserResource)
                .toList();

        return new PageImpl<>(userResources, pageRequest, users.getTotalElements());
    }


    private User checkUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    private Shop checkShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Shop not found!"));
    }
}
