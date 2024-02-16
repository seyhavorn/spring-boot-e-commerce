package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.ShipmentRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.ShipmentResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Shipment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ShipmentMapper {

    public ShipmentResourceDto shipmentToShipmentResourceDto(Shipment shipment) {
        ShipmentResourceDto shipmentResourceDto = new ShipmentResourceDto();
        BeanUtils.copyProperties(shipment, shipmentResourceDto);
        return shipmentResourceDto;
    }

    public Shipment shipmentRequestDtoToShipment(ShipmentRequestDto shipmentRequestDto) {
        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(shipmentRequestDto, shipment);
        return shipment;
    }
}
