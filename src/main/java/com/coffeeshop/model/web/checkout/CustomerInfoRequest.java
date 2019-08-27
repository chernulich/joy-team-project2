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
class CustomerInfoRequest {

    @ApiModelProperty(example = "Pedro production")
    private String entityName;

    @ApiModelProperty(example = "dioped@site.com")
    private String email;

    @ApiModelProperty(example = "+972585489627")
    private String phoneNumber;

    private ContactInfoRequest contacts;

}
