package com.coffeeshop.controller;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.exception.ProductNotFoundException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.repository.ProductItemRepository;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/productItem")
public class ProductItemManagementController {

    @Autowired
    private ProductItemManagementService productItemManagementService;


    @PostMapping("/add")
    public void createProductItems(@RequestBody List<@Valid ProductItemRequest> productItemRequests, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        try {
            productItemManagementService.createProductItems(productItemRequests);
        } catch (ProductNotFoundException e) {
            e.httpStatus();
        }
    }

    @GetMapping("/findAndMark")
    public List<ProductItemRequest> findAndMarkAsSold() {
        return new ArrayList<>();
    }

}
