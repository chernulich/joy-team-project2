package com.coffeeshop.model.web.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerInfo {

    private String entityName;
    private String email;
    private String phoneNumber;
    private ContactInfo contactInfo;

}
