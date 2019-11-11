package com.coffeeshop.model.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ProductCoffeeDto {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    @ApiModelProperty(example = "2")
    private Integer sour;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    @ApiModelProperty(example = "2")
    private Integer bitter;

    @Min(value = 1)
    @Max(value = 5)
    @NotNull
    @ApiModelProperty(example = "2")
    private Integer strong;

    @NotNull
    @ApiModelProperty(example = "true")
    private Boolean ground;

    @NotNull
    @ApiModelProperty(example = "false")
    private Boolean decaf;

    @NotNull
    @ApiModelProperty(example = "arabica")
    private String coffeeType;
}
