package com.coffeeshop.repository;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.type.ProductStatus;

import java.util.List;

public interface ProductItemQueryRepository {

    List<ProductItem> findProductItemByProductAndProductStatusLimitIs(Product product, ProductStatus status, int limit);

}
