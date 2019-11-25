package com.coffeeshop.controller.admin;

import com.coffeeshop.exception.InputValidationException;
import com.coffeeshop.model.web.admin.OrderListRequest;
import com.coffeeshop.model.web.admin.OrderListResponse;
import com.coffeeshop.repository.admin.OrderListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
@PropertySource(value = "classpath:defaultDtoValues.properties")
public class OrderListController {

    @Value(value = "${default.page.size}")
    private Integer defaultPageSize;

    @Value(value = "${default.result.size}")
    private Integer defaultResultSize;

    @Value(value = "${default.max.result.size}")
    private Integer defaultMaxResultSize;

    private final OrderListRepository orderListRepository;

    public OrderListController(OrderListRepository orderListRepository) {
        this.orderListRepository = orderListRepository;
    }


    @PostMapping
    public List<OrderListResponse> getOrderList(@RequestBody @Valid OrderListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new InputValidationException(result);
        }

        if (request.getPage() == null) {
            request.setPage(defaultPageSize);
        }

        if (request.getResult() == null) {
            request.setResult(defaultResultSize);
        } else if (request.getResult() > 20) {
            request.setResult(defaultMaxResultSize);
        }

        return orderListRepository.getOrderListByQuery(request);
    }


}
