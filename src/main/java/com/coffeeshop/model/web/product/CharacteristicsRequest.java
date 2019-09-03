package com.coffeeshop.model.web.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class CharacteristicsRequest {

    @ApiModelProperty(example = "1")
    private Integer bitterFrom;

    @ApiModelProperty(example = "5")
    private Integer bitterTo;

    @ApiModelProperty(example = "1")
    private Integer sourFrom;

    @ApiModelProperty(example = "3")
    private Integer sourTo;

    @ApiModelProperty(example = "1")
    private Integer strongFrom;

    @ApiModelProperty(example = "3")
    private Integer strongTo;

    @ApiModelProperty(example = "false")
    private Boolean decaf;

    @ApiModelProperty(example = "true")
    private Boolean ground;

    @ApiModelProperty(example = "arabica")  //There is no such field in the database
    private String coffeeType;

}
