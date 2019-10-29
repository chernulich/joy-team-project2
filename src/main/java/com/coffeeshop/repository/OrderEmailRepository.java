package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEmailRepository extends JpaRepository<OrderEmail,Long> {
}
