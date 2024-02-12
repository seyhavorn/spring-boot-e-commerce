package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.ShopRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShopResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ShopMapper {
    public ShopResourceDto shopToShopResourceDto(Shop shop) {
        ShopResourceDto shopResourceDto = new ShopResourceDto();
        BeanUtils.copyProperties(shop, shopResourceDto);
        return shopResourceDto;
    }

    public Shop shopRequestDtoToShop(ShopRequestDto shopRequestDto) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopRequestDto, shop);
        return shop;
    }
}
