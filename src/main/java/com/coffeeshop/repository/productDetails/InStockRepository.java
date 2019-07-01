package com.coffeeshop.repository.productDetails;

import com.coffeeshop.model.entity.productDetails.InStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InStockRepository extends JpaRepository<InStock, Long> {
}
