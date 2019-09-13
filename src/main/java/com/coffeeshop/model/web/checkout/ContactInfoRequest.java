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
public class ContactInfoRequest {

    @ApiModelProperty(example = "Pedro")
    private String firstName;

    @ApiModelProperty(example = "Huant")
    private String lastName;

    @ApiModelProperty(example = "+972585896177")
    private String phoneNumber;
}
