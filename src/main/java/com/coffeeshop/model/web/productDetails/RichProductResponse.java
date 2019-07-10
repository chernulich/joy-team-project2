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
    private Integer quantityAvailableKg;
    private String[] productImages;
    private CharacteristicResponse characteristicResponse;
    private String description;
    private InStockResponse inStockResponse;
    private Double unitPrice;
}
