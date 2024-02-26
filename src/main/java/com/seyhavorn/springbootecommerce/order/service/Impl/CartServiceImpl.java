package com.seyhavorn.springbootecommerce.order.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.helper.Message;
import com.seyhavorn.springbootecommerce.order.dto.request.CartRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.CartResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.Cart;
import com.seyhavorn.springbootecommerce.shop.entity.Customer;
import com.seyhavorn.springbootecommerce.order.mapper.CartMapper;
import com.seyhavorn.springbootecommerce.order.repository.CartRepository;
import com.seyhavorn.springbootecommerce.shop.repository.CustomRepository;
import com.seyhavorn.springbootecommerce.order.service.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CustomRepository customRepository;
    private final FilterSpecificationService<Cart> filterSpecificationService;
    private final CartMapper cartMapper;

    @Override
    @CacheEvict(value = "carts", allEntries = true)
    public CartResourceDto create(CartRequestDto cartRequestDto) {
        Customer customer = checkCustomer(cartRequestDto.getCustomer_id());
        Cart cart = cartMapper.cartRequestDtoToCart(cartRequestDto);
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return cartMapper.cartToCartResource(cart);
    }

    @Override
    public Page<CartResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public CartResourceDto update(CartRequestDto cartRequestDto, Long id) {
        return null;
    }

    @Override
    public CartResourceDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteBranch(Long id) {

    }

    @Override
    @Cacheable(value = "carts", key = "#customerId")
    public List<CartResourceDto> findCartByCustomerId(Long customerId) {
        Customer customer = checkCustomer(customerId);
        List<Cart> carts = cartRepository.findCartsByCustomer_Id(customer.getId());
        return carts.stream().map(cartMapper::cartToCartResource).toList();
    }

    private Customer checkCustomer(Long customerId) {
        return customRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
    }
}
