package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderConfirmationEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderConfirmationEmailRepository extends JpaRepository<OrderConfirmationEmail,Long> {
}
