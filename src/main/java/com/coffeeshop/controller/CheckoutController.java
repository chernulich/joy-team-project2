package com.coffeeshop.controller;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;
import com.coffeeshop.service.checkout.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer/")
public class CheckoutController {

    private final CheckoutServiceImpl checkoutService;

    @Autowired
    public CheckoutController(CheckoutServiceImpl checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/checkout")
    public CheckoutResponse checkout(@RequestBody CheckoutRequest request, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }

        return checkoutService.checkout(request);
    }
}
