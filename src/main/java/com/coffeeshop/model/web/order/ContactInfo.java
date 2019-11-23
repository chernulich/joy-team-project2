package com.coffeeshop.model.web.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ContactInfo {

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
