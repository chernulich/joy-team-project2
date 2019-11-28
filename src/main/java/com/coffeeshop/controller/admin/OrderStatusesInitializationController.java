package com.coffeeshop.controller.admin;

import com.coffeeshop.model.admin.OrderStatusesResponse;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class OrderStatusesInitializationController {

    @GetMapping("/initialization")
    public OrderStatusesResponse getStatuses() {
        return OrderStatusesResponse.builder()
                .orderStatus(OrderStatus.values())
                .orderPaymentStatus(OrderPaymentStatus.values())
                .orderTransitStatus(OrderTransitStatus.values())
                .build();
    }
}
