package com.coffeeshop.controller;

import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @PostMapping("/add")
    public void createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        productManagementService.createProduct(productCreateRequest);
    }

    @PostMapping("/addImage/{id}")
    public void addImage(@PathVariable("id") Long productId, @RequestBody byte[] image) {
        productManagementService.addProductImage(productId, image);
    }

    @PostMapping("/true/{id}")
    public void makeAvailable(@PathVariable("id") Long productId) {
        productManagementService.makeAvailable(productId);
    }

    @PostMapping("/false/{id}")
    public void makeUnavailable(@PathVariable("id") Long productId) {
        productManagementService.makeUnavailable(productId);
    }
}
