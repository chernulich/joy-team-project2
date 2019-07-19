package com.coffeeshop.model.web.checkout;

import lombok.*;

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
