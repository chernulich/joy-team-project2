package com.coffeeshop.service.email;

import com.coffeeshop.model.entity.OrderEmail;

public interface OrderEmailCompletionTemplate {

    OrderEmail createOrderCompletionEmail(String email, String firstName, String lastName, Long orderId);
}
