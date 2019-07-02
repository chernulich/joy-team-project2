package com.coffeeshop.model.web.checkout;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerInfoDtoRequest {

    private String entityName;
    private String email;
    private String phoneNumber;
    private ContactInfoDtoRequest contactInfoDtoRequest;
    private DeliveryDtoRequest deliveryDtoRequest;
    private List<ProductsDtoRequest> productsDtoRequestList;
    private ChargesDtoRequest chargesDtoRequest;


}
