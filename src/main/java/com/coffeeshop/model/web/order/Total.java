package com.coffeeshop.model.web.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Total {
    private Double subTotal;
    private Double shipping;
    private Double orderTotal;
}
