package com.coffeeshop.model.web;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InStockDtoResponse {

    private Boolean isAvailable;
    private Integer quantityAvailable;
}
