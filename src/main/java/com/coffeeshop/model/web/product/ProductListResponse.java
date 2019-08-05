package com.coffeeshop.model.web.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListResponse {

    private ProductResponse popular;
    private List<ProductResponse> products;
}
