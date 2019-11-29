package com.coffeeshop.controller.admin;

import com.coffeeshop.model.web.admin.StatusRequest;
import com.coffeeshop.service.admin.OrderStatusService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
public class OrderPaymentStatusControllerAdmin {

    private final OrderStatusService orderStatusService;

    public OrderPaymentStatusControllerAdmin(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PutMapping("/{orderId}/payment")
    public void updateOrderPaymentStatus(@PathVariable("orderId") Long orderId, StatusRequest statusRequest) {
        orderStatusService.updateOrderPaymentStatus(orderId, statusRequest);
    }
}
