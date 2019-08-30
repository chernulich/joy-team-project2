package com.coffeeshop.model.web.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

//    private Long productId;
    private String title;
    private String shortDescription;
//    private String type;
    private Double unitPrice;
    private String previewImage;

//    private Integer quantity; //inStockCount
//    private ProductParametersResponse productParametersResponse;


}
