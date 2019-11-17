package com.coffeeshop.service.email;

import com.coffeeshop.model.entity.OrderEmail;

import java.io.IOException;

public interface OrderEmailConfirmationTemplate {
    OrderEmail createOrderConfirmationEmail(String email, String firstName, String lastName, Long orderId) throws IOException;
}
