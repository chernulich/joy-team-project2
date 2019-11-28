package com.coffeeshop.service.admin;

import com.coffeeshop.model.web.admin.StatusRequest;

public interface OrderStatusService {

    void updateOrderStatus(Long orderId, StatusRequest statusRequest);
    void updateOrderPaymentStatus(Long orderId, StatusRequest statusRequest);
    void updateOrderTransitStatus(Long orderId, StatusRequest statusRequest);
}
