package com.coffeeshop.service.admin;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.model.web.admin.StatusRequest;
import com.coffeeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService  {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderStatusServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, StatusRequest statusRequest) {
        Orders order = findOrder(orderId);
        String status = statusRequest.getStatus();
        OrderStatus orderStatus = OrderStatus.getByName(status);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderPaymentStatus(Long orderId, StatusRequest statusRequest) {
        Orders order = findOrder(orderId);
        String status = statusRequest.getStatus();
        OrderPaymentStatus paymentStatus = OrderPaymentStatus.getByName(status);
        order.setOrderPaymentStatus(paymentStatus);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderTransitStatus(Long orderId, StatusRequest statusRequest) {
        Orders order = findOrder(orderId);
        String status = statusRequest.getStatus();
        OrderTransitStatus transitStatus = OrderTransitStatus.valueOf(status);
        order.setOrderTransitStatus(transitStatus);
        orderRepository.save(order);
    }

    private Orders findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
    }
}
