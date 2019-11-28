package com.coffeeshop.model.web.admin;

import com.coffeeshop.model.entity.type.OrderPaymentStatus;
import com.coffeeshop.model.entity.type.OrderStatus;
import com.coffeeshop.model.entity.type.OrderTransitStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderListResponse {

    private Long id;

    private LocalDateTime createdDate;

    private String companyName;

    private String contactName;

    private String phone;

    private String email;

    private Double orderTotal;

    private OrderTransitStatus transitStatus;

    private OrderPaymentStatus paymentStatus;

    private OrderStatus orderStatus;

    private String customerComment;


}
