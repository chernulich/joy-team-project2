package com.coffeeshop.model.entity.type;


import java.util.Arrays;


public enum OrderPaymentStatus {

    NO_INFO(1, "NO_INFO"),
    PAY_ON_DELIVERY(2, "PAY_ON_DELIVERY"),
    PAID_ON_DELIVERY(3, "PAID_ON_DELIVERY"),
    PAID_BY_CC(4, "PAID_BY_CC");

    private Integer id;
    private String name;

    OrderPaymentStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static OrderPaymentStatus getById(Integer id) {
        if (id == null) {
            return OrderPaymentStatus.NO_INFO;
        }

        return Arrays.stream(values())
                .filter(orderPaymentStatus -> orderPaymentStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public static OrderPaymentStatus getByName(String name) {
        return Arrays.stream(values())
                .filter(orderPaymentStatus -> orderPaymentStatus.getName().equals(name))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
    public String getName() { return name; }
}
