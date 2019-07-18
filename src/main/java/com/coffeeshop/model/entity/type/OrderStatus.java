package com.coffeeshop.model.entity.type;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OrderStatus {

    UNPROCESSED(1),
    IN_PROGRESS(2),
    CLOSED(3),
    ESCALATED(4);

    private Integer id;

    public static OrderStatus getById(Integer id) {
        if (id == null) {
            return OrderStatus.UNPROCESSED;
        }

        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
}
