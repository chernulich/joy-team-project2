package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductCategory;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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

    @NotNull
    private Double unitPrice;
    
    private ProductCategory productCategory;

    private ProductCoffeeDto productCoffee;

    //private ProductTeaDto productTea;

}
