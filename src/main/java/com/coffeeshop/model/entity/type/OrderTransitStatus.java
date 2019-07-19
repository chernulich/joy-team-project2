package com.coffeeshop.model.entity.type;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OrderTransitStatus {

    NEW_ORDER(1),
    SHIPPED(2),
    DELIVERED(3),
    RETURNED(4);

    private Integer id;

    public static OrderTransitStatus getById(Integer id) {
        if (id == null) {
            return OrderTransitStatus.NEW_ORDER;
        }

        return Arrays.stream(values())
                .filter(orderTransitStatus -> orderTransitStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
}
