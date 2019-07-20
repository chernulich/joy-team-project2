package com.coffeeshop.model.entity.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ProductStatus {

    AVAILABLE (1) ,
    SOLD (2);

    private Integer id;

    public static ProductStatus getById(Integer id) {
        if (id == null) {
            return ProductStatus.AVAILABLE;
    }

        return Arrays.stream(values())
                .filter(productStatus -> productStatus.getId().equals(id))
                .findFirst()
                .get();
    }
}
