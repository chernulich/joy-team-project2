package com.coffeeshop.model.web.image;

import com.coffeeshop.annotation.ImageConstraint;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ImageRequest {

    private Long productId;

    @NotBlank
    @ImageConstraint
    private String image;

}
