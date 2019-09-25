package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.repository.search.ProductSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductSearchRepository {

    @Query("select p.id from Product p")
    Optional<List<Long>> getAllIds();

    Optional<Product> findProductByIdAndAvailableIsTrue(Long id);
}
