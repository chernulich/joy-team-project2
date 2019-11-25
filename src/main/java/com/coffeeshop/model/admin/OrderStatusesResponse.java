package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusesResponse {

    private OrderStatus[] orderStatus;
    private OrderTransitStatus[] orderTransitStatus;
    private OrderPaymentStatus[] orderPaymentStatus;
}
