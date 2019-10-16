package com.coffeeshop.service.email;

import com.coffeeshop.exception.OrderNotFoundException;
import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderEmailType;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class OrderEmailCancellationTemplateImpl implements OrderEmailCancellationTemplate {

    private final OrderRepository orderRepository;
    private final OrderEmailRepository orderEmailRepository;

    @Autowired
    public OrderEmailCancellationTemplateImpl(OrderRepository orderRepository, OrderEmailRepository orderEmailRepository) {
        this.orderRepository = orderRepository;
        this.orderEmailRepository = orderEmailRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderEmail createOrderCancellationEmail(String email, String firstName, String lastName, Long orderId, String reason) throws IOException {

        Orders order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

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

    private String createMessage(Long orderId, String firstName, String lastName, String reason) throws IOException {
        String message = getTemplate();
        message = message.replaceAll("\\$\\{firstName}", firstName)
                        .replaceAll("\\$\\{lastName}", lastName)
                        .replaceAll("\\$\\{reason}", reason)
                        .replaceAll("\\$\\{orderId}", Long.toString(orderId));
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    private static String getTemplate() throws IOException {
        String template;
        FileInputStream fis = new FileInputStream("src/main/resources/templates/orderEmailCancellationTemplate.html");
        template = IOUtils.toString(fis, StandardCharsets.UTF_8);
        return template;
    }
}
