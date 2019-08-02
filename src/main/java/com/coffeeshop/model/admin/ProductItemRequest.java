package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductStatus;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductItemRequest {

    @NotNull
    private Long productId;

    @Min(value = 1)
    @NotNull
    private Integer weightKg;

    @NotNull
    private ProductStatus productStatus;
}
