package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDto {


    private String shortDescription;

    private String description;

    private String previewImage;

    private Double unitPrice;

    private ProductCategory productCategory;

    private boolean available;

    private ProductCoffeeDto productCoffee;

    //private ProductTeaDto productTea;

}
