package com.coffeeshop.model.web.checkout;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ChargesDtoRequest {

    private Integer shipping;
    private Double tax;
}
