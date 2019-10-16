package com.coffeeshop.runner;

import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.service.email.OrderEmailCancellationTemplate;
import com.coffeeshop.service.email.OrderEmailCancellationTemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Component
@Profile("dev")
public class TestToCancellationEmail implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderEmailRepository orderEmailRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Orders order = Orders.builder()
                .orderStatus(OrderStatus.IN_PROGRESS)
                .orderTransitStatus(OrderTransitStatus.NEW_ORDER)
                .orderPaymentStatus(OrderPaymentStatus.NO_INFO).build();
        orderRepository.save(order);
        OrderEmailCancellationTemplate template = new OrderEmailCancellationTemplateImpl(orderRepository, orderEmailRepository);
        OrderEmail orderEmail = template.createOrderCancellationEmail("sacha@11list.ru", "Alex", "Chernulich",
                1L, "don't need");
        System.out.println(new String(Base64.getDecoder().decode(orderEmail.getEmailParts())));
    }
}
