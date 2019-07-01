package com.coffeeshop.model.entity.productDetails;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank
    private String productName;

    @NotNull
    private Integer quantityAvailableKg;

    private String[] productImages;

    @NotBlank
    private String description;

    @NotNull
    private Double unitPrice;
}
