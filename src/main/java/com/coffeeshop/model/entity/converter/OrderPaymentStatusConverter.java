package com.coffeeshop.model.entity.converter;

import com.coffeeshop.model.entity.type.OrderPaymentStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderPaymentStatusConverter implements AttributeConverter<OrderPaymentStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OrderPaymentStatus orderPaymentStatus) {
        return orderPaymentStatus == null ? OrderPaymentStatus.NO_INFO.getId() : orderPaymentStatus.getId();
    }

    @Override
    public OrderPaymentStatus convertToEntityAttribute(Integer id) {
        return id == null ? OrderPaymentStatus.NO_INFO : OrderPaymentStatus.getById(id);
    }
}
