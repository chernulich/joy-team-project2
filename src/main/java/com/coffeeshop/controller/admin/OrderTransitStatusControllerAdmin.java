package com.coffeeshop.controller.admin;

import com.coffeeshop.model.web.admin.StatusRequest;
import com.coffeeshop.service.admin.OrderStatusService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
public class OrderTransitStatusControllerAdmin {

    private final OrderStatusService orderStatusService;

    public OrderTransitStatusControllerAdmin(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PutMapping("/{orderId}/transit")
    public void updateOrderTransitStatus(@PathVariable("orderId") Long orderId, StatusRequest statusRequest) {

        orderStatusService.updateOrderTransitStatus(orderId, statusRequest);
    }
}
