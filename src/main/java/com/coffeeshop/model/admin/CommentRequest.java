package com.coffeeshop.model.admin;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentRequest {

    @NotBlank
    private String comment;
}
