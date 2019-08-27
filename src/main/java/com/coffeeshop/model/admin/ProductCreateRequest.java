package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ProductCreateRequest {

    @NotBlank
    @ApiModelProperty(example = "Alabasta AAA")
    private String productName;

    @NotBlank
    @ApiModelProperty(example = "He's just our office plankton, don't mind him.")
    private String shortDescription;

    @Length(min = 6, max = 500)
    @ApiModelProperty(example = "A typical office worker, which usually has certain tasks assigned to them without hopes of getting a promotion or achieving something in his field.")
    private String description;

    @ApiModelProperty(example = "http://customer-ui/products/1/image/1")
    private String previewImage;

    @Min(value = 1)
    @NotNull
    @ApiModelProperty(example = "100.99")
    private Double unitPrice;

    private ProductCategory productCategory;

    @Valid
    @NotNull
    private ProductCoffeeDto productCoffeeDto;

}
