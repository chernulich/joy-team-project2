package com.coffeeshop.service.admin;

import com.coffeeshop.model.web.admin.StatusRequest;

import java.io.IOException;

public interface OrderStatusService {

    void updateOrderStatus(Long orderId, StatusRequest statusRequest) throws IOException;
    void updateOrderStatus(Long orderId, String reason, StatusRequest statusRequest) throws IOException;
    void updateOrderPaymentStatus(Long orderId, StatusRequest statusRequest) throws IOException;
    void updateOrderTransitStatus(Long orderId, StatusRequest statusRequest) throws IOException;
}
