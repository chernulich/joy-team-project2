package com.coffeeshop.model.common;

public enum CoffeeType {

    ARABICA(1),
    ROBUSTA(2);

    private final Integer id;

    CoffeeType(Integer id) {
        this.id = id;
    }
}
