package com.coffeeshop.model.web.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ProductRequest {


    @ApiModelProperty(example = "1")
    @Min(1)
    private Integer page;

    @ApiModelProperty(example = "10")
    @Min(1)
    private Integer results;

    @ApiModelProperty(example = "Ala")
    private String search;

    @ApiModelProperty(example = "10")
    private Double priceMin;

    @ApiModelProperty(example = "100")
    private Double priceMax;

    @ApiModelProperty(example = "price")
    private String sortBy;

    private CharacteristicsRequest characteristics;

}
