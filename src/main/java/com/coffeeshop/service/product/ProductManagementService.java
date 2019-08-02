package com.coffeeshop.service.product;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.model.entity.ProductCoffee;


public interface ProductManagementService {

    void createProductAndQuantity(ProductCreateRequest productCreateRequest);
    ProductCoffee createProductCoffee(ProductCoffeeDto productCoffeeDto);
    void makeAvailable(Long productId);
    void makeUnavailable(Long productId);

}
