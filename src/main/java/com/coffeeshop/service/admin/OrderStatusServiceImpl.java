package com.coffeeshop.service.admin;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.OrderDetails;
import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.model.web.admin.StatusRequest;
import com.coffeeshop.repository.OrderDetailsRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.service.email.OrderEmailCancellationTemplate;
import com.coffeeshop.service.email.OrderEmailCompletionTemplate;
import com.coffeeshop.service.email.OrderEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class OrderStatusServiceImpl implements OrderStatusService  {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderEmailCancellationTemplate orderEmailCancellationTemplate;
    private final OrderEmailCompletionTemplate orderEmailCompletionTemplate;
    private final OrderEmailSendService orderEmailSendService;

    @Autowired
    public OrderStatusServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, OrderEmailCancellationTemplate orderEmailCancellationTemplate, OrderEmailCompletionTemplate orderEmailCompletionTemplate, OrderEmailSendService orderEmailSendService) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderEmailCancellationTemplate = orderEmailCancellationTemplate;
        this.orderEmailCompletionTemplate = orderEmailCompletionTemplate;
        this.orderEmailSendService = orderEmailSendService;
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, StatusRequest statusRequest) throws IOException {
        Orders order = findOrder(orderId);
        String newStatus = statusRequest.getNewStatus();
        OrderStatus orderStatus = OrderStatus.getByName(newStatus);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        switch (orderStatus) {
            case COMPLETED: sendOrderCompletedEmail(order);
                break;
        }
    }

    private void sendOrderCompletedEmail(Orders order) throws IOException {
        OrderDetails orderDetails = orderDetailsRepository.findByOrder(order);
        OrderEmail orderEmail = orderEmailCompletionTemplate.createOrderCompletionEmail(
                orderDetails.getOrderEmail(), orderDetails.getContactFirstName(),
                orderDetails.getContactLastName(), order.getId());
        orderEmailSendService.sendEmail(orderEmail);
    }

    @Override
    public void updateOrderStatus(Long orderId, String reason, StatusRequest statusRequest) throws IOException {
        Orders order = findOrder(orderId);
        String newStatus = statusRequest.getNewStatus();
        OrderStatus orderStatus = OrderStatus.getByName(newStatus);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        switch (orderStatus) {
            case CANCELED: sendOrderCanceledEmail(order, reason);
                break;
        }
    }

    private void sendOrderCanceledEmail(Orders order, String reason) throws IOException {
        OrderDetails orderDetails = orderDetailsRepository.findByOrder(order);
        OrderEmail orderEmail = orderEmailCancellationTemplate.createOrderCancellationEmail(
                orderDetails.getOrderEmail(), orderDetails.getContactFirstName(),
                orderDetails.getContactLastName(), order.getId(), reason);
        orderEmailSendService.sendEmail(orderEmail);
    }

    @Override
    @Transactional
    public void updateOrderPaymentStatus(Long orderId, StatusRequest statusRequest) throws IOException {
        Orders order = findOrder(orderId);
        String newStatus = statusRequest.getNewStatus();
        OrderPaymentStatus paymentStatus = OrderPaymentStatus.getByName(newStatus);
        order.setOrderPaymentStatus(paymentStatus);
        orderRepository.save(order);
        switch (paymentStatus) {
            case PAID_ON_DELIVERY: updateOrderTransitStatus(orderId, StatusRequest.builder().newStatus("DELIVERED").build());
                break;
        }
    }

    @Override
    @Transactional
    public void updateOrderTransitStatus(Long orderId, StatusRequest statusRequest) throws IOException {
        Orders order = findOrder(orderId);
        String newStatus = statusRequest.getNewStatus();
        OrderTransitStatus transitStatus = OrderTransitStatus.valueOf(newStatus);
        order.setOrderTransitStatus(transitStatus);
        orderRepository.save(order);
        switch (transitStatus) {
            case DELIVERED: updateOrderStatus(orderId, StatusRequest.builder().newStatus("COMPLETED").build());
                break;
        }
    }

    private Orders findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
    }
}
