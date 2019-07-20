package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPriceRepository extends JpaRepository<OrderPrice, Long> {
}
