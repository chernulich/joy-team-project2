package com.coffeeshop.model.entity.type;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ProductCategory {

    COFFEE(1);

    private Integer id;

    public static ProductCategory getById(Integer id) {

        if (id == null) {
            return ProductCategory.COFFEE;
        }

        return Arrays.stream(values())
                .filter(productCategory -> productCategory.getId().equals(id))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
}
