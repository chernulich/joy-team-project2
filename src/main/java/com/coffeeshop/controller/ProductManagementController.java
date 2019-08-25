package com.coffeeshop.controller;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.service.product.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin/product")
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @PostMapping("/add")
    public void createProductAndQuantity(@RequestBody @Valid ProductCreateRequest productCreateRequest, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        productManagementService.createProductAndQuantity(productCreateRequest);
    }


    @PostMapping("/makeAvailable/{id}")
    public void makeAvailable(@PathVariable("id") Long productId) {

        productManagementService.makeAvailable(productId);
    }

    @PostMapping("/makeUnavailable/{id}")
    public void makeUnavailable(@PathVariable("id") Long productId) {
        productManagementService.makeUnavailable(productId);
    }

}