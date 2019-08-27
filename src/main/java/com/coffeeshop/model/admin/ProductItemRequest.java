package com.coffeeshop.model.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel
public class ProductItemRequest {

    @NotNull
    @ApiModelProperty(example = "1")
    private Long productId;

    @Min(value = 1)
    @NotNull
    @ApiModelProperty(example = "20")
    private Integer weightKg;

}
