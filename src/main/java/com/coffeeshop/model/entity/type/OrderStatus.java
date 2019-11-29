package com.coffeeshop.model.entity.type;

import java.util.Arrays;

public enum OrderStatus {

    UNPROCESSED(1, "UNPROCESSED"),
    IN_PROGRESS(2, "IN_PROGRESS"),
    CLOSED(3, "CLOSED"),
    ESCALATED(4, "ESCALATED");

    private Integer id;
    private String name;

    OrderStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static OrderStatus getById(Integer id) {
        if (id == null) {
            return OrderStatus.UNPROCESSED;
        }

        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public static OrderStatus getByName(String name) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.getName().equals(name))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
    public String getName() { return name; }
}
