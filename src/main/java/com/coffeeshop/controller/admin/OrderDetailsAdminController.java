package com.coffeeshop.controller.admin;

import com.coffeeshop.model.web.order.RichOrderDetailsResponse;
import com.coffeeshop.service.order.OrderDetailsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderDetailsAdminController {

    private final OrderDetailsAdminService orderDetailsAdminService;

    @Autowired
    public OrderDetailsAdminController(OrderDetailsAdminService orderDetailsAdminService) {
        this.orderDetailsAdminService = orderDetailsAdminService;
    }

    @GetMapping("/{id}")
    public RichOrderDetailsResponse getOrderDetails(@PathVariable("id") Long orderId) {
        return orderDetailsAdminService.getOrderDetails(orderId);
    }
}
