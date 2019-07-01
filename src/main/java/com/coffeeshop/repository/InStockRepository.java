package com.coffeeshop.repository;

import com.coffeeshop.model.entity.InStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InStockRepository extends JpaRepository<InStock, Long> {
}
