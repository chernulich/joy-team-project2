package com.coffeeshop.model.entity.converter;

import com.coffeeshop.model.entity.type.OrderEmailType;

import javax.persistence.AttributeConverter;

public class OrderEmailTypeConverter implements AttributeConverter<OrderEmailType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrderEmailType orderEmailType) {
        return orderEmailType == null ? OrderEmailType.ORDER_CONFIRMATION.getId() : orderEmailType.getId();
    }

    @Override
    public OrderEmailType convertToEntityAttribute(Integer id) {
        return id == null ? OrderEmailType.ORDER_CONFIRMATION : OrderEmailType.getById(id);
    }
}
