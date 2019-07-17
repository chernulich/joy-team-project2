package com.coffeeshop.model.web.checkout;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CheckoutRequest {

    private CustomerInfoRequest customerInfo;
    private DeliveryRequest delivery;
    private List<ProductWeightQuantityRequest> products;
    private ChargesRequest charges;
}
