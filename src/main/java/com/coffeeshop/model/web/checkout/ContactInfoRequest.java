package com.coffeeshop.model.web.checkout;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ContactInfoRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
}
