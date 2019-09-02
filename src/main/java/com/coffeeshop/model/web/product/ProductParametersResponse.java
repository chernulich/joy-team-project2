package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParametersResponse {

    private Integer strong;
    private Integer bitter;
    private Integer sour;
    private Boolean decaf;
//    private String coffeeType;

}
