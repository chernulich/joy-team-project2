package com.coffeeshop.service.email;

import com.coffeeshop.model.entity.OrderEmail;

public interface OrderEmailCancellationTemplate {

    OrderEmail createOrderCancellationEmail(String email, String firstName, String lastName, Long orderId, String reason);
}
