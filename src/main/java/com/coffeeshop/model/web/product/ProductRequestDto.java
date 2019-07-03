package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    private Integer page;
    private Integer results;
    private Double priceMin;
    private Double priceMax;
    private String sortBy;
    private CharacteristicsRequestDto characteristics;

}
