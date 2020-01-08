package com.coffeeshop.service.email;

import com.coffeeshop.exception.order.OrderException;
import com.coffeeshop.exception.order.OrderExceptionType;
import com.coffeeshop.model.entity.OrderEmail;
import com.coffeeshop.repository.OrderEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class OrderEmailSendServiceImpl implements OrderEmailSendService {

    private JavaMailSender javaMailSender;

    private OrderEmailRepository orderEmailRepository;

    @Value("${mail.sender}")
    private String senderEmail;

    @Value("${email.lock.time}")
    private Integer emailLockTime;

    @Autowired
    public OrderEmailSendServiceImpl(JavaMailSender javaMailSender, OrderEmailRepository orderEmailRepository) {
        this.javaMailSender = javaMailSender;
        this.orderEmailRepository = orderEmailRepository;
    }

    @Scheduled(cron = "${cron.expression}")
    @Transactional
    public void checkingStatusOrderEmails() {
        List<OrderEmail> orders = orderEmailRepository.findAllByIsSendFailedTrue();
        orders.forEach(orderEmail -> sendEmail(orderEmail));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public OrderEmail sendEmail(OrderEmail orderEmail) {
        Long orderId = orderEmail.getId();
        orderEmail = orderEmailRepository.findById(orderId).orElseThrow(() -> new OrderException(orderId, OrderExceptionType.ORDER_NOT_FOUND));
        try {
            emailSender(orderEmail);
        }
        catch (MailSendException | MailAuthenticationException | MessagingException ex) {
            ex.printStackTrace();
        }
        return orderEmail;
    }

    private void emailSender(OrderEmail orderEmail) throws MailSendException, MailAuthenticationException, MessagingException {
        String body = new String(Base64.getDecoder().decode(orderEmail.getEmailParts()));
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(orderEmail.getOrderEmail());
        mimeMessageHelper.setSubject(orderEmail.getOrderEmailType().getName());
        mimeMessageHelper.setText(body, true);

        if (orderEmail.getIsSendFailed() && !orderEmail.getIsLocked()
                ||  orderEmail.getIsSendFailed() && orderEmail.getIsLocked()
                && LocalDateTime.now().minusMinutes(emailLockTime).isAfter(orderEmail.getLockAcquiredOn())) {
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            orderEmail.setIsLocked(false);
            orderEmail.setIsSendFailed(false);
            orderEmail.setLockAcquiredOn(LocalDateTime.now());
        }
    }
}