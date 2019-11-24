package com.coffeeshop.controller.admin;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.repository.OrderRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
public class OrderStatusControllerAdmin {

    private final OrderRepository orderRepository;

    public OrderStatusControllerAdmin(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{orderId}/order")
    public void updateOrderStatus(@PathVariable("orderId") Long orderId, String status) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        OrderStatus orderStatus = OrderStatus.getByName(status);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }
}
