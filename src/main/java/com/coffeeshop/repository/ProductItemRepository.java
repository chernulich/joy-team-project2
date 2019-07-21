package com.coffeeshop.repository;

import com.coffeeshop.model.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {

}
