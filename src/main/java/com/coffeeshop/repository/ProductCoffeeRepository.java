package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCoffeeRepository extends JpaRepository<ProductCoffee, Long> {

    Optional<ProductCoffee> findProductCoffeeByProduct(Product product);
}
