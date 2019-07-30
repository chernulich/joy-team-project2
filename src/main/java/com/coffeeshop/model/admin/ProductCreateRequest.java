package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductCategory;
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
public class ProductCreateRequest {

    @NotBlank
    private String shortDescription;

    @Length(min = 6, max = 500)
    private String description;

    private String previewImage;

    @Min(value = 1)
    @NotNull
    private Double unitPrice;

    private ProductCategory productCategory;

    @Valid
    @NotNull
    private ProductCoffeeDto productCoffee;

}
