package com.coffeeshop.model.web.admin;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {

    @Length(max = 100)
    private String fullName;

    @Email
    private String email;

    @NotNull
    private String avatarImage;

    private Boolean isTestUser;

}
