package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEmailRepository extends JpaRepository<OrderEmail,Long> {
    List<OrderEmail> findAllByIsSendFailedTrue();
}