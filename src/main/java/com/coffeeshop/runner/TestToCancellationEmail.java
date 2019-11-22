package com.coffeeshop.runner;

import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.service.email.OrderEmailCancellationTemplate;

import com.coffeeshop.service.email.OrderEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class TestToCancellationEmail implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderEmailRepository orderEmailRepository;
    private final OrderEmailCancellationTemplate orderEmailCancellationTemplate;
    private final OrderEmailSendService orderEmailSendService;

    @Autowired
    public TestToCancellationEmail(OrderRepository orderRepository, OrderEmailRepository orderEmailRepository,
                                   OrderEmailCancellationTemplate orderEmailCancellationTemplate, OrderEmailSendService orderEmailSendService) {
        this.orderRepository = orderRepository;
        this.orderEmailRepository = orderEmailRepository;
        this.orderEmailCancellationTemplate = orderEmailCancellationTemplate;
        this.orderEmailSendService = orderEmailSendService;
    }

    @Override
    public void run(String... args) throws Exception {
        Orders order = Orders.builder()
                .orderStatus(OrderStatus.IN_PROGRESS)
                .orderTransitStatus(OrderTransitStatus.NEW_ORDER)
                .orderPaymentStatus(OrderPaymentStatus.NO_INFO).build();
        orderRepository.save(order);
        Orders order2 = Orders.builder()
                .orderStatus(OrderStatus.IN_PROGRESS)
                .orderTransitStatus(OrderTransitStatus.NEW_ORDER)
                .orderPaymentStatus(OrderPaymentStatus.NO_INFO).build();
        orderRepository.save(order2);
        OrderEmail orderEmail = orderEmailCancellationTemplate.createOrderCancellationEmail(
                "chernulich.alex@gmail.com", "Alex", "Chernulich", 1L, "You don't need it");
        OrderEmail orderEmail2 = orderEmailCancellationTemplate.createOrderCancellationEmail(
                "chernulich.alex@gmail.com", "Alex", "Chernulich", 2L, "You don't need it");
        orderEmailSendService.sendEmail(orderEmail);
    }
}