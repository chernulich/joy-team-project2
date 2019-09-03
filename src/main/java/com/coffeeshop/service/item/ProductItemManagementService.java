package com.coffeeshop.service.item;

import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;

import java.util.List;

public interface ProductItemManagementService {

    void createProductItems(List<ProductItemRequest> productItemRequests);
    List<ProductItemResponse> findAndMarkAsSold(Integer amount);
    void productQuantityUpdate();
}
