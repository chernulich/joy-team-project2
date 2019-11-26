package com.coffeeshop.service.admin;

public interface OrderStatusService {

    void updateOrderStatus(Long orderId, String status);
    void updateOrderPaymentStatus(Long orderId, String status);
    void updateOrderTransitStatus(Long orderId, String status);
}
