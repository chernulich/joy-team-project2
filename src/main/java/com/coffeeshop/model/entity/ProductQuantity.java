package com.coffeeshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "PRODUCT_QUANTITY")
public class ProductQuantity extends BaseDate{

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;


    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "VERSION")
    @Version
    private Long version;


}
