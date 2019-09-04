package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;
    private String title;
    private String shortDescription;
    private String type;
    private Double price;
    private String previewImage;
    private Integer availableAmount;
    private ProductParametersResponse productParametersResponse;

}
