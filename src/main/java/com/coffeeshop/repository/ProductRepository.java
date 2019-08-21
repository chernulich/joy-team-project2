package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p.id from Product p")
    List<Long> getAllIds();
}
