package com.coffeeshop.repository;

import com.coffeeshop.model.entity.ProductCoffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCoffeeRepository extends JpaRepository<ProductCoffee, Long> {
}
