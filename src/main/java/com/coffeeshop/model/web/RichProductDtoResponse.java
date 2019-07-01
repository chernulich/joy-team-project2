package com.coffeeshop.model.web;

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
    private List<CharacteristicDtoResponse>  characteristicResponseList;
    private String description;
    private List<InStockDtoResponse> inStockResponseList;
    private Double unitPrice;
}
