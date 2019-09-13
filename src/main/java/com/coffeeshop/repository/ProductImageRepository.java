package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Optional<List<ProductImage>> findProductImagesByProduct(Product product);
}
