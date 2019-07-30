package com.coffeeshop.service;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.entity.ProductCoffee;

import java.io.IOException;

public interface ProductManagementService {

    void createProduct(ProductCreateRequest productCreateRequest) throws IOException;
    ProductCoffee createProductCoffee(ProductCoffeeDto productCoffeeDto);
    void addProductImage(Long productId, byte[] image);
    void makeAvailable(Long productId);
    void makeUnavailable(Long productId);

}
