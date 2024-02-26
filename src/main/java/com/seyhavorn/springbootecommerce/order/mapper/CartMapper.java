package com.seyhavorn.springbootecommerce.order.mapper;

import com.seyhavorn.springbootecommerce.order.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.CartResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.Cart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CartResourceDto cartToCartResource(Cart cart) {
        CartResourceDto cartResourceDto = new CartResourceDto();
        BeanUtils.copyProperties(cart, cartResourceDto);

        if (cart.getCustomer() != null && cart.getCustomer().getId() != null) {
            cartResourceDto.setCustomer_id(cart.getCustomer().getId());
        }

        return cartResourceDto;
    }

    public Cart cartRequestDtoToCart(CartRequestDto cartRequestDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartRequestDto, cart);
        return cart;
    }
}
