package com.coffeeshop.runner;

import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.service.email.OrderEmailCancellationTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Component
@Profile("dev")
public class TestToCancellationEmail implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final OrderEmailRepository orderEmailRepository;
    private final OrderEmailCancellationTemplate orderEmailCancellationTemplate;

    @Autowired
    public TestToCancellationEmail(OrderRepository orderRepository, OrderEmailRepository orderEmailRepository, OrderEmailCancellationTemplate orderEmailCancellationTemplate) {
        this.orderRepository = orderRepository;
        this.orderEmailRepository = orderEmailRepository;
        this.orderEmailCancellationTemplate = orderEmailCancellationTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Orders order = Orders.builder()
                .orderStatus(OrderStatus.IN_PROGRESS)
                .orderTransitStatus(OrderTransitStatus.NEW_ORDER)
                .orderPaymentStatus(OrderPaymentStatus.NO_INFO).build();
        orderRepository.save(order);
        OrderEmail orderEmail = orderEmailCancellationTemplate.createOrderCancellationEmail(
                "chernulich.alex@gmail.com", "Alex", "Chernulich", 1L, "You don't need it");
        System.out.println(new String(Base64.getDecoder().decode(orderEmail.getEmailParts())));
        System.out.println(orderEmail.getOrderEmail());
    }
}
