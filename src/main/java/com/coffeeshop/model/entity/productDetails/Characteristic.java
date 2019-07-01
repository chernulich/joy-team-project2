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
@Table(name="Characteristics")
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @NotNull
    private Integer strong;

    @NotNull
    private Integer sour;

    @NotNull
    private Integer bitter;
}
