package com.coffeeshop.model.entity.type;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OrderPaymentStatus {

    NO_INFO(1),
    PAY_ON_DELIVERY(2),
    PAID_ON_DELIVERY(3),
    PAID_BY_CC(4);

    private Integer id;

    public static OrderPaymentStatus getById(Integer id) {
        if (id == null) {
            return OrderPaymentStatus.NO_INFO;
        }

        return Arrays.stream(values())
                .filter(orderPaymentStatus -> orderPaymentStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
}
