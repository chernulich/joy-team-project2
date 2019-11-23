package com.coffeeshop.controller.admin;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.admin.OrderListRequest;
import com.coffeeshop.model.web.admin.OrderListResponse;
import com.coffeeshop.repository.admin.OrderListRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class OrderListController {

    private final OrderListRepository orderListRepository;

    public OrderListController(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }


    @PostMapping
    public List<OrderListResponse> getOrderList(@RequestBody @Valid OrderListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }
        return orderListRepository.getOrderListByQuery(request);
    }


}
