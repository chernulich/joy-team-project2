package com.coffeeshop.model.common;

public enum ProductType {

    COFFEE(1),
    TEA(2);

    private final Integer id;

    ProductType(Integer id) {
        this.id = id;
    }
}
