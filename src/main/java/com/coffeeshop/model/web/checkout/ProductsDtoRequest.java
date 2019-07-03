package com.coffeeshop.model.web.checkout;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductsDtoRequest {

    private Long productId;
    private Double weight;
    private Integer quantity;
}
