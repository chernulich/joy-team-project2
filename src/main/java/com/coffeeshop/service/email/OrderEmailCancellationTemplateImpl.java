package com.coffeeshop.service.email;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderEmailType;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class OrderEmailCancellationTemplateImpl implements OrderEmailCancellationTemplate {


    private static final String ORDER_EMAIL_CANCELLATION_TEMPLATE;

    private final OrderRepository orderRepository;
    private final OrderEmailRepository orderEmailRepository;

    static {
        ORDER_EMAIL_CANCELLATION_TEMPLATE = getTemplate();
    }
    @Autowired
    public OrderEmailCancellationTemplateImpl(OrderRepository orderRepository, OrderEmailRepository orderEmailRepository) {
        this.orderRepository = orderRepository;
        this.orderEmailRepository = orderEmailRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public OrderEmail createOrderCancellationEmail(String email, String firstName, String lastName, Long orderId, String reason) {

        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));

        String base64Message = createMessage(orderId, firstName, lastName, reason);

        OrderEmail orderEmail = OrderEmail.builder()
                .orderEmail(email)
                .orderEmailType(OrderEmailType.ORDER_CANCELLATION)
                .lockAcquiredOn(LocalDateTime.now())
                .isLocked(false)
                .isSendFailed(true)
                .emailParts(base64Message)
                .order(order).build();
        orderEmailRepository.save(orderEmail);
        return orderEmail;
    }

    private String createMessage(Long orderId, String firstName, String lastName, String reason) {
        String message = ORDER_EMAIL_CANCELLATION_TEMPLATE;
        message = message.replaceAll("\\$\\{firstName}", firstName)
                .replaceAll("\\$\\{lastName}", lastName)
                .replaceAll("\\$\\{reason}", reason)
                .replaceAll("\\$\\{orderId}", orderId.toString());
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    @SneakyThrows
    private static String getTemplate() {
        try (InputStream inputStream = OrderEmailCancellationTemplateImpl.class.getResourceAsStream
                ("/templates/orderEmailCancellationTemplate.html")) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }
}