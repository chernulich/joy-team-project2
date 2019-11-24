package com.coffeeshop.model.web.order;

import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsResponse {

    private Long orderId;
    private LocalDateTime orderDate;
    private Double orderTotal;
    private OrderTransitStatus orderTransitStatus;
    private OrderPaymentStatus orderPaymentStatus;
    private OrderStatus orderStatus;
}
