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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class OrderEmailSendServiceImpl implements OrderEmailSendService {

    private JavaMailSender javaMailSender;

    private OrderEmailRepository orderEmailRepository;

    @Value("${mail.sender}")
    private String senderEmail;

    @Autowired
    public OrderEmailSendServiceImpl(JavaMailSender javaMailSender, OrderEmailRepository orderEmailRepository) {
        this.javaMailSender = javaMailSender;
        this.orderEmailRepository = orderEmailRepository;
    }

    @Override
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

    @Transactional(propagation = Propagation.SUPPORTS)
    void emailSender(OrderEmail orderEmail) throws MailSendException, MailAuthenticationException, MessagingException {
        String body = new String(Base64.getDecoder().decode(orderEmail.getEmailParts()));
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(orderEmail.getOrderEmail());
        mimeMessageHelper.setSubject(orderEmail.getOrderEmailType().getName());
        mimeMessageHelper.setText(body, true);

        javaMailSender.send(mimeMessageHelper.getMimeMessage());
        orderEmail.setIsLocked(false);
        orderEmail.setIsSendFailed(false);
        orderEmail.setLockAcquiredOn(LocalDateTime.now());
    }

}