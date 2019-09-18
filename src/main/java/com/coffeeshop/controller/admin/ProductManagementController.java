package com.coffeeshop.controller.admin;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductCreateRequest;
import com.coffeeshop.service.product.ProductManagementService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/product")
public class ProductManagementController {

    private final ProductManagementService productManagementService;

    @Autowired
    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @PostMapping("/create")
    @SneakyThrows
    public void createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest, BindingResult result) {
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
