package com.coffeeshop.controller.test;

import com.coffeeshop.model.admin.ProductItemResponse;
import com.coffeeshop.service.item.ProductItemManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestFindMarkAsSoldController {

    private final ProductItemManagementServiceImpl productItemManagementService;

    @Autowired
    public TestFindMarkAsSoldController(ProductItemManagementServiceImpl productItemManagementService) {
        this.productItemManagementService = productItemManagementService;
    }

    @GetMapping("/test/findAndMark/{id}/{amount}/{id1}/{amount1}")
    public List<ProductItemResponse> findAndMarkAsSold(@PathVariable("id") Long id , @PathVariable("amount") Integer amount,
                                                       @PathVariable("id1") Long id1 , @PathVariable("amount1") Integer amount1){

        Map<Long, Integer> request = new HashMap<>();
        request.put(id, amount);
        request.put(id1, amount1);

        return productItemManagementService.findAndMarkAsSold(request);
    }
    @GetMapping("/test/findAndMark/{id}/{amount}")
    public List<ProductItemResponse> findAndMarkAsSold(@PathVariable("id") Long id , @PathVariable("amount") Integer amount){
        Map<Long, Integer> request = new HashMap<>();
        request.put(id, amount);

        return productItemManagementService.findAndMarkAsSold(request);
    }
}
