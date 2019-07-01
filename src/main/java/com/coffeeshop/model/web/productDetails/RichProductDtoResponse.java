package com.coffeeshop.model.web.productDetails;

import com.coffeeshop.model.web.productDetails.CharacteristicDtoResponse;
import com.coffeeshop.model.web.productDetails.InStockDtoResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RichProductDtoResponse {

    private Long id;
    private String productName;
    private Integer quantityAvailableKg;
    private String[] productImages;
    private CharacteristicDtoResponse  characteristicDtoResponseList;
    private String description;
    private InStockDtoResponse inStockDtoResponseList;
    private Double unitPrice;
}
