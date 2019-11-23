package com.coffeeshop.repository.admin;

import com.coffeeshop.model.entity.Orders;
import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsCommentsRepository extends JpaRepository<OrderDetailsComments, Long> {
    List<OrderDetailsComments> findByOrder(Orders order);
}
