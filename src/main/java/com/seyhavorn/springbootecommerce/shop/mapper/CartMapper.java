package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.CartResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Cart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CartResourceDto cartToCartResource(Cart cart) {
        CartResourceDto cartResourceDto = new CartResourceDto();
        BeanUtils.copyProperties(cart, cartResourceDto);
        return cartResourceDto;
    }

    public Cart cartRequestDtoToCart(CartRequestDto cartRequestDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartRequestDto, cart);
        return cart;
    }
}
