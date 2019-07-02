package com.coffeeshop.model.web.productDetails;

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
