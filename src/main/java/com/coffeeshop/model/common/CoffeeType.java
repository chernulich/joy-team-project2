package com.coffeeshop.model.common;

import java.util.Arrays;

public enum CoffeeType {

    ARABICA(1, "arabica"),
    ROBUSTA(2, "robusta"),
    LIBERICA(3, "liberica"),
    EXCELSA(4, "excelsa");

    private Integer id;
    private String name;

    CoffeeType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public String  getName() {
        return name;
    }

    public static CoffeeType getById(Integer id) {

        if (id == null) {
            return CoffeeType.ARABICA;
        }

        return Arrays.stream(values())
                .filter(coffeeType -> coffeeType.getId().equals(id))
                .findFirst()
                .get();
    }
    public static CoffeeType getByName(String name) {

        if (name == null) {
            return CoffeeType.ARABICA;
        }

        return Arrays.stream(values())
                .filter(coffeeType -> coffeeType.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .get();
    }
}
