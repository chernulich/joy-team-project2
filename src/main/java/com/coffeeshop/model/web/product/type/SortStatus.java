package com.coffeeshop.model.web.product.type;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum SortStatus {

    POPULAR(1),
    PRICE(2),
    NAME(3);

    private Integer id;

    public static SortStatus getById(Integer id) {
        if (id == null) {
            return SortStatus.PRICE;
        }
        return Arrays.stream(values())
                .filter(sortStatus -> sortStatus.getId().equals(id))
                .findFirst()
                .get();
    }

    public Integer getId() {
        return id;
    }
}
