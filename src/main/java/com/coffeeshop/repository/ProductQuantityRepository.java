package com.coffeeshop.repository;

import com.coffeeshop.model.entity.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity,Long> {

    Optional<ProductQuantity> findByProductId(Long id);
}
