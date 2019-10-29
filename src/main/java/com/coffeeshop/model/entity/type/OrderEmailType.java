package com.coffeeshop.model.entity.type;

import java.util.Arrays;

public enum OrderEmailType {
    ORDER_CONFIRMATION(1, "Order Confirmation"),
    ORDER_CANCELLATION(2, "Order Cancellation"),
    ORDER_COMPLETION(3, "Order Completion");

    private Integer id;
    private String name;

    OrderEmailType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

public static OrderEmailType getById(Integer id) {
    if (id == null) {
        return OrderEmailType.ORDER_CONFIRMATION;
    }

    return Arrays.stream(values())
            .filter(orderEmailType -> orderEmailType.getId().equals(id))
            .findFirst()
            .get();
}
public static OrderEmailType getByName(String name) {
    if (name == null) {
        return OrderEmailType.ORDER_CONFIRMATION;
    }

    return Arrays.stream(values())
            .filter(orderEmailType -> orderEmailType.getName().equals(name))
            .findFirst()
            .get();
}

    public Integer getId() {
        return id;
    }
    public String getName() { return name; }

}
