package com.coffeeshop.model.web.product;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListResponseDto {

    private ProductDto popular;
    private List<ProductDto> products;
}
