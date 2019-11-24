package com.coffeeshop.model.web.order;

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
    private OrderDetailsResponse orderDetails;
    private List<ProductResponse> products;
    private List<OrderDetailsCommentsInfo> comments;
    private Total total;

}
