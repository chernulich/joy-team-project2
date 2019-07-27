package com.coffeeshop.model.admin;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCoffeeDto {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer sour;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer bitter;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    private Integer strong;

    @NotNull
    private Boolean ground;

    @NotNull
    private Boolean decaf;
}
