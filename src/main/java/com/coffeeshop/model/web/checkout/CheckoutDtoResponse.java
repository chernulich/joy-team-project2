package com.coffeeshop.model.web.checkout;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CheckoutDtoResponse {

    private Long orderId;
    private String message;
}
