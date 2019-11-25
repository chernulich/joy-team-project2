package com.coffeeshop.repository.admin;

import com.coffeeshop.model.web.admin.OrderListRequest;
import com.coffeeshop.model.web.admin.OrderListResponse;

import java.util.List;

public interface OrderListRepository {

    List<OrderListResponse> getOrderListByQuery(OrderListRequest request);
}
