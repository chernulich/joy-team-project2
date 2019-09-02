package com.coffeeshop.model.web.productDetails;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RichProductResponse {

    private Long id;
    private String productName;
    private String description;
    private String shortDescription;
    private String[] productImages;
    private String previewImage;
    private CharacteristicResponse characteristicResponse;
    private Integer amountAvailable;
    private Double price;
}
