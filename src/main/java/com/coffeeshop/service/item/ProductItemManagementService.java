package com.coffeeshop.service.item;

import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.ProductItem;

import java.util.List;
import java.util.Map;

public interface ProductItemManagementService {

    void createProductItems(List<ProductItemRequest> productItemRequests);

    List<ProductItemResponse> findAndMarkAsSold(Map<Long, Integer> items);

    List<ProductItem> findAndMarkAsSold(Product product, Integer amount);
}
