package com.coffeeshop.model.web.product.type;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum SortStatus {

    POPULAR(""),
    PRICE(" p.unitPrice, p.productName"),
    NAME(" p.productName, p.unitPrice");

    private final String name;

    public String toString() {
        return this.name;
    }

}
