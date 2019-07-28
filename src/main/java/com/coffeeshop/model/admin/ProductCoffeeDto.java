package com.coffeeshop.model.admin;

import com.coffeeshop.model.admin.deserializer.CoffeeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = CoffeeDeserializer.class)
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
