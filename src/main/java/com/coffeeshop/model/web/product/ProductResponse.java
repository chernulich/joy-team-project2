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
//    private String type;
    private String previewImage;
    private Double unitPrice;
    private Integer quantity; //inStockCount
//    private ProductParametersResponse productParametersResponse;


}
