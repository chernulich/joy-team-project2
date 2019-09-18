package com.coffeeshop.controller.admin;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/productItem")
public class ProductItemManagementController {

    private final ProductItemManagementService productItemManagementService;

    @Autowired
    public ProductItemManagementController(ProductItemManagementService productItemManagementService) {
        this.productItemManagementService = productItemManagementService;
    }

    @PostMapping("/create")
    public void createProductItems(@RequestBody List<@Valid ProductItemRequest> productItemRequests, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        productItemManagementService.createProductItems(productItemRequests);

    }
}
