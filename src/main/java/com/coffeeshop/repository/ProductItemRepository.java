package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long>,ProductItemQueryRepository {

    Optional<ProductItem> findProductItemByProduct(Product product);
}
