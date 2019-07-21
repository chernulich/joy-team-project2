package com.coffeeshop.repository;

import com.coffeeshop.model.entity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity,Long> {
}
