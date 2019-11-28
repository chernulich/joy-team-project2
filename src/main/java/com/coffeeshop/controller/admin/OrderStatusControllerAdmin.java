package com.coffeeshop.controller.admin;

import com.coffeeshop.model.web.admin.StatusRequest;
import com.coffeeshop.service.admin.OrderStatusService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
public class OrderStatusControllerAdmin {

    private final OrderStatusService orderStatusService;

    public OrderStatusControllerAdmin(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PutMapping("/{orderId}/order")
    public void updateOrderStatus(@PathVariable("orderId") Long orderId, StatusRequest statusRequest) {
        orderStatusService.updateOrderStatus(orderId, statusRequest);
    }
}
