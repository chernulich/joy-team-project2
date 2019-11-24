package com.coffeeshop.model.web.order;

import com.coffeeshop.model.common.CoffeeType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;
    private String productUrl;
    private String productName;
    private CoffeeType type;
    private Integer quantity;
    private Double unitPrice;
    private Double price;
}
