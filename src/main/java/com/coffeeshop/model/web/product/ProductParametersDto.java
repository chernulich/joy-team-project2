package com.coffeeshop.model.web.product;

import com.coffeeshop.model.common.CoffeeType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParametersDto {

    private String flavour;
    private Double rate;
    private Boolean decaf;
    private String coffeeType;

}
