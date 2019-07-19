package com.coffeeshop.model.entity.converter;

import com.coffeeshop.model.entity.type.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus == null ? OrderStatus.UNPROCESSED.getId() : orderStatus.getId();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer id) {
        return id == null ? OrderStatus.UNPROCESSED : OrderStatus.getById(id);
    }
}
