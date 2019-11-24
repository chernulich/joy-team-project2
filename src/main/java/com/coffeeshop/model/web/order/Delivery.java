package com.coffeeshop.model.web.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Delivery {

    private String officialName;
    private String city;
    private String street;
    private String houseNumber;
    private String apartment;
    private String floor;
    private String deliveryComment;

}
