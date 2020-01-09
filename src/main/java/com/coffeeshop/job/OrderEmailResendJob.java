package com.coffeeshop.job;

import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.repository.OrderEmailRepository;
import com.coffeeshop.service.email.OrderEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderEmailResendJob {

    @Value("${email.lock.time}")
    private Integer emailLockTime;

    private final OrderEmailRepository orderEmailRepository;
    private final OrderEmailSendService orderEmailSendService;

    @Autowired
    public OrderEmailResendJob(OrderEmailRepository orderEmailRepository, OrderEmailSendService orderEmailSendService) {
        this.orderEmailRepository = orderEmailRepository;
        this.orderEmailSendService = orderEmailSendService;
    }

    @Scheduled(cron = "${cron.expression}")
    @Transactional
    public void findAllWithIsSendFailedTrueAndResend() {
        List<OrderEmail> orders = orderEmailRepository.findAllByIsSendFailedTrue();
        orders.forEach(orderEmail -> {
            if (orderEmailIsValidToSend(orderEmail)) {
                orderEmailSendService.sendEmail(orderEmail);
            }
        });
    }

    private boolean orderEmailIsValidToSend(OrderEmail orderEmail) {
        return orderEmail.getIsSendFailed() && !orderEmail.getIsLocked()
                ||  orderEmail.getIsSendFailed() && orderEmail.getIsLocked()
                && LocalDateTime.now().minusMinutes(emailLockTime).isAfter(orderEmail.getLockAcquiredOn());
    }
}
