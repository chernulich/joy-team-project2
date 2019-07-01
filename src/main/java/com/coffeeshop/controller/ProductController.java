package com.coffeeshop.controller;

import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.web.RichProductDtoResponse;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products/{productId}")
    public Product getById(@PathVariable("id") Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
    @GetMapping("/products")
    public List<Product> getAll() {
        return productRepository.findAll();
    }


}
