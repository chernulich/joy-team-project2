package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductCategory;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import springfox.documentation.spring.web.json.Json;

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

    private String object;

    //private ProductTeaDto productTea;
}
