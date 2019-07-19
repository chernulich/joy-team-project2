package com.coffeeshop.model.entity;

import com.coffeeshop.model.entity.converter.OrderPaymentStatusConverter;
import com.coffeeshop.model.entity.converter.OrderStatusConverter;
import com.coffeeshop.model.entity.converter.OrderTransitStatusConverter;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ORDERS")
public class Orders extends BaseDate {

    @Column(name = "ORDER_TRANSIT_STATUS")
    @Convert(converter = OrderTransitStatusConverter.class)
    private OrderTransitStatus orderTransitStatus;

    @Column(name = "ORDER_PAYMENT_STATUS")
    @Convert(converter = OrderPaymentStatusConverter.class)
    private OrderPaymentStatus orderPaymentStatus;

    @Column(name = "ORDER_STATUS")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

}
