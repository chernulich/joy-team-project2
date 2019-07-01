package com.coffeeshop.model.entity.productDetails;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name="InStock")
public class InStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    private Integer quantityAvailable;
}
