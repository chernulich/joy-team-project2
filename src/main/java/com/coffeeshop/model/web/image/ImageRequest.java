package com.coffeeshop.model.web.image;

import com.coffeeshop.validation.ImageTypeConstraint;
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
    @ImageTypeConstraint
    private String image;

}
