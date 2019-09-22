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

    @ApiModelProperty(dataType = "List<file>", example = "[image/Image_Brazilian Santos_Product Listing.png," +
            "image/Image_Kenya_Product Description.png]")
    private List<String> base64Images;
}
