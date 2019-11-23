package com.coffeeshop.model.web.order;

import com.coffeeshop.model.entity.OrderDetails;
import com.coffeeshop.model.entity.Product;
import com.coffeeshop.model.entity.admin.OrderDetailsComments;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RichOrderDetailsResponse {

    private CustomerInfo customerInfo;

    private Delivery delivery;

    private OrderDetails orderDetails;

    private List<Product> products;

    private List<OrderDetailsComments> comments;

    private Total total;


}
