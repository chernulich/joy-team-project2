package com.coffeeshop.repository.productDetails;

import com.coffeeshop.model.entity.productDetails.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
