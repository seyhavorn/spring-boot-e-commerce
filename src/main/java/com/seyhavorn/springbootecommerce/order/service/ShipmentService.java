package com.seyhavorn.springbootecommerce.order.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.request.ShipmentRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.ShipmentResourceDto;
import org.springframework.data.domain.Page;

public interface ShipmentService {
    ShipmentResourceDto create(ShipmentRequestDto shipmentRequestDto);

    Page<ShipmentResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    ShipmentResourceDto update(ShipmentRequestDto shipmentRequestDto);

    ShipmentResourceDto findById(Long Id);
}
