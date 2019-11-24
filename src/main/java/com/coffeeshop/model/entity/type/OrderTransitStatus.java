package com.coffeeshop.model.entity.type;

import java.util.Arrays;

public enum OrderTransitStatus {

    NEW_ORDER(1, "New order"),
    SHIPPED(2, "Shipped"),
    DELIVERED(3, "Delivered"),
    RETURNED(4, "Returned");

    private Integer id;
    private String name;

    OrderTransitStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static OrderTransitStatus getById(Integer id) {
        if (id == null) {
            return OrderTransitStatus.NEW_ORDER;
        }

        return Arrays.stream(values())
                .filter(orderTransitStatus -> orderTransitStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public static OrderTransitStatus getByName(String name) {
        return Arrays.stream(values())
                .filter(orderTransitStatus -> orderTransitStatus.getName().equals(name))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
    public String getName() { return name; }
}
