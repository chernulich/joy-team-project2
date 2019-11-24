package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderItems;
import com.coffeeshop.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    List<OrderItems> findAllByOrder(Orders order);
}
