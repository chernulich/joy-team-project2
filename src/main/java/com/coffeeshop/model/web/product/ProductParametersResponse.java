package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParametersResponse {

    private String flavour;
    private Double rate;
    private Boolean decaf;
    private String coffeeType;

}
