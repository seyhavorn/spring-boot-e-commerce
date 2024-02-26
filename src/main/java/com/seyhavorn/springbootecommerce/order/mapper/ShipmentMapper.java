package com.seyhavorn.springbootecommerce.order.mapper;

import com.seyhavorn.springbootecommerce.order.dto.request.ShipmentRequestDto;
import com.seyhavorn.springbootecommerce.order.dto.resource.ShipmentResourceDto;
import com.seyhavorn.springbootecommerce.order.entity.Shipment;
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
