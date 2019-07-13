package com.coffeeshop.model.web.checkout;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
class CustomerInfoRequest {

    private String entityName;
    private String email;
    private String phoneNumber;
    private ContactInfoRequest contacts;

}
