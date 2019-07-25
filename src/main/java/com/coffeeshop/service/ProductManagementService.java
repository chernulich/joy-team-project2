package com.coffeeshop.service;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateDto;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductCoffee;

public interface ProductManagementService {

    Product createProduct(ProductCreateDto productCreateDto);
    ProductCoffee createProductCoffee(ProductCreateDto productCreateDto);
    void addProductImage(Long productId, byte[] image);

}
