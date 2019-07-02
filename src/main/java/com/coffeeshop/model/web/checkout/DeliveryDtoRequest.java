package com.coffeeshop.model.web.checkout;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DeliveryDtoRequest {

    private String officialName;
    private  String city;
    private String street;
    private String houseNumber;
    private String apartmemt;
    private Integer floor;
    private String deliveryComment;
}
