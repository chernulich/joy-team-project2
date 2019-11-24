package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderDetails;
import com.coffeeshop.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findByOrder(Orders order);
}
