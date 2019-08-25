package com.coffeeshop.model.web.checkout;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ApiModel
public class ProductWeightQuantityRequest {

    @ApiModelProperty(example = "123123")
    private Long productId;

    private Double weight;

    @ApiModelProperty(example = "10")
    private Integer quantity;
}
