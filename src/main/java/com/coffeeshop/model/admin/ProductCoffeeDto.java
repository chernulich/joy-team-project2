package com.coffeeshop.model.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCoffeeDto {

    private Integer sour;

    private Integer bitter;

    private Integer strong;

    private Boolean ground;

    private Boolean decaf;
}
