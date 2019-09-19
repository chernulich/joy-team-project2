package com.coffeeshop.model.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ProductImageRequest {

    @NotNull
    @ApiModelProperty(example = "3")
    private Long productId;

    @ApiModelProperty(dataType = "List<file>", example = "[image/coffee.jpg," +
            "ui/customer-ui/server/mock/image/rose-blue-flower-rose-blooms-67636.jpeg]")
    private List<String> base64Images;
}
