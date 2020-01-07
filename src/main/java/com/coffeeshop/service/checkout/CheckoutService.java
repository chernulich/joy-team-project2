package com.coffeeshop.service.checkout;

import com.coffeeshop.model.web.checkout.CheckoutRequest;
import com.coffeeshop.model.web.checkout.CheckoutResponse;

import java.io.IOException;

public interface CheckoutService {

    CheckoutResponse checkout(CheckoutRequest request) throws IOException;
}
