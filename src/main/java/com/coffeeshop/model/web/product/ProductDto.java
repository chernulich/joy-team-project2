package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long productId;
    private String title;
    private String shortDescription;
    private String type;
    private String previewImage;
    private Double price;
    private Integer inStockCount;
    private ProductParametersResponse productParametersResponse;


}
