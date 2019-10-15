package com.coffeeshop.service.email;

import com.coffeeshop.exception.OrderNotFoundException;
import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.type.OrderEmailType;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class OrderEmailCancellationTemplateImpl implements OrderEmailCancellationTemplate {

    private final OrderRepository orderRepository;
    private final OrderEmailRepository orderEmailRepository;

//    @Value("${email}")
//    private String email;

    @Autowired
    public OrderEmailCancellationTemplateImpl(OrderRepository orderRepository, OrderEmailRepository orderEmailRepository) {
        this.orderRepository = orderRepository;
        this.orderEmailRepository = orderEmailRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderEmail createOrderCancellationEmail(String email, String firstName, String lastName, Long orderId, String reason) {

        Orders order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        String base64Message = createMessage(email, firstName, lastName, reason);

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

    private String createMessage(String email, String firstName, String lastName, String reason) {
        Path path = Paths.get("/src/main/resources/templates/orderEmailCancellationTemplate.html");

        return "";
    }
}
