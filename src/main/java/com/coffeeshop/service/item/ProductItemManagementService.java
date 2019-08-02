package com.coffeeshop.service.item;

import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;

import java.util.List;

public interface ProductItemManagementService {

    List<ProductItemResponse> createProductItems(List<ProductItemRequest> productItemRequests);
    List<ProductItemResponse> findAndMarkAsSold(Long id);
}
