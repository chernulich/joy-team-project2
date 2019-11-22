package com.coffeeshop.repository;

import com.coffeeshop.model.entity.OrderEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderEmailRepository extends JpaRepository<OrderEmail,Long> {
//    @Query("select oe from OrderEmail oe where oe.isSendFailed = true ")
    List<OrderEmail> findAllByIsSendFailedTrue();
}