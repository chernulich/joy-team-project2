package com.coffeeshop.service.email;

import com.coffeeshop.model.entity.OrderEmail;

public interface OrderEmailSendService {

    OrderEmail sendEmail(OrderEmail orderEmail);
}