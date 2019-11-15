package com.coffeeshop.repository.admin;

import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsCommentsRepository extends JpaRepository<OrderDetailsComments, Long> {
}
