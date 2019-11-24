package com.coffeeshop.model.web.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ManagementUserInfo {

    private String firstName;
    private String lastName;
    private String avatarImage;

}
