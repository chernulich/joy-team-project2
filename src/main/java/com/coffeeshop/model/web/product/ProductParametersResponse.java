package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParametersResponse {

    private Integer strong;
    private Integer sour;
    private Integer bitter;
    private Boolean decaf;
    private Boolean ground;
    private String coffeeType;

}
