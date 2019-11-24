package com.coffeeshop.controller.admin;


import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.repository.OrderRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/orders")
public class OrderPaymentStatusControllerAdmin {

    private final OrderRepository orderRepository;

    public OrderPaymentStatusControllerAdmin(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{orderId}/payment")
    public void updateOrderStatus(@PathVariable("orderId") Long orderId, String status) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        OrderPaymentStatus paymentStatus = OrderPaymentStatus.getByName(status);
        order.setOrderPaymentStatus(paymentStatus);
        orderRepository.save(order);
    }
}
