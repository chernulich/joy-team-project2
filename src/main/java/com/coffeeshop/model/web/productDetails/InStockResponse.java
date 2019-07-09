package com.coffeeshop.model.web.productDetails;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InStockResponse {

    private Boolean isAvailable;
    private Integer quantityAvailable;
}
