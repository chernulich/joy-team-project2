package com.coffeeshop.controller.customer;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.admin.ProductItemRequest;
import com.coffeeshop.model.entity.ProductItem;
import com.coffeeshop.repository.ProductItemRepository;
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
    private ProductItemRepository productItemRepository;


    @PostMapping("/add")
    public void createProductItems(@RequestBody List<@Valid ProductItemRequest> productItemRequests, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        productItemManagementService.createProductItems(productItemRequests);

    }

    @PutMapping("/findAndMark")
    public List<ProductItem> findAndMarkAsSold() {
        return productItemRepository.findAll();
    }

}
