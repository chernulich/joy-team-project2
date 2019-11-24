package com.coffeeshop.service.order;

import com.coffeeshop.model.web.order.RichOrderDetailsResponse;

public interface OrderDetailsAdminService {

    RichOrderDetailsResponse getOrderDetails(Long orderId);
}
