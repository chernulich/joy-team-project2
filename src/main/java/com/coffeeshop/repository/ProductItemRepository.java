package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.type.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {

    Optional<ProductItem> findProductItemByProduct(Product product);
    List<ProductItem> findAllByProductStatusAndProduct(ProductStatus status, Product product);
}
