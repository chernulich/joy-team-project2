package com.coffeeshop.controller.customer;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.service.item.ProductItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
        productItemManagementService.createProductItems(productItemRequests);

    }

    @PutMapping("/findAndMark")
    public List<ProductItemResponse> findAndMarkAsSold(@RequestBody Map<Long, Integer> items) {
        return productItemManagementService.findAndMarkAsSold(items);
    }

}
