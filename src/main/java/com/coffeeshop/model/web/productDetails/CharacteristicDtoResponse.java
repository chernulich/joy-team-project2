package com.coffeeshop.model.web.productDetails;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CharacteristicDtoResponse {

    private Integer strong;
    private Integer sour;
    private Integer bitter;
}
