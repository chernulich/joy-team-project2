package com.coffeeshop.model.admin;

import com.coffeeshop.model.entity.type.ProductStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductItemResponse {

    private Long id;
    private Long productId;
    private Integer weightKG;
    private ProductStatus status;
    private Integer version;
}
