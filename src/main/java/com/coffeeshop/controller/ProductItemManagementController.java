package com.coffeeshop.controller;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.model.entity.ProductQuantity;
import com.coffeeshop.repository.ProductQuantityRepository;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/productItem")
public class ProductItemManagementController {

    @Autowired
    private ProductItemManagementService productItemManagementService;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @PostMapping("/add")
    public List<ProductItemResponse> createProductItems(@RequestBody @Valid List<ProductItemRequest> productItemRequests, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return productItemManagementService.createProductItems(productItemRequests);
    }

    @GetMapping("/get/{id}")
    public List<ProductItemResponse> findAndMarkAsSold(@PathVariable("id") Long id) {
        return productItemManagementService.findAndMarkAsSold(id);
    }

    @GetMapping("/getQ")
    public List<ProductQuantity> findAndMarkAsSold() {
        return productQuantityRepository.findAll();
    }

}
