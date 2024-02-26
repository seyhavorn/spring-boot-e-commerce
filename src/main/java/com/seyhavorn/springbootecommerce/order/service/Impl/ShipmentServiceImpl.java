package com.seyhavorn.springbootecommerce.order.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.order.dto.request.ShipmentRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.ShipmentResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.Shipment;
import com.seyhavorn.springbootecommerce.order.mapper.ShipmentMapper;
import com.seyhavorn.springbootecommerce.order.repository.ShipmentRepository;
import com.seyhavorn.springbootecommerce.order.service.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final FilterSpecificationService<Shipment> filterSpecificationService;
    private final ShipmentMapper shipmentMapper;

    @Override
    public ShipmentResourceDto create(ShipmentRequestDto shipmentRequestDto) {
        Shipment shipment = shipmentMapper.shipmentRequestDtoToShipment(shipmentRequestDto);
        shipmentRepository.save(shipment);
        return shipmentMapper.shipmentToShipmentResourceDto(shipment);
    }

    @Override
    public Page<ShipmentResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public ShipmentResourceDto update(ShipmentRequestDto shipmentRequestDto) {
        return null;
    }

    @Override
    public ShipmentResourceDto findById(Long Id) {
        return null;
    }
}
