package com.coffeeshop.model.entity;

import com.coffeeshop.model.entity.converter.ProductStatusConverter;
import com.coffeeshop.model.entity.type.ProductStatus;
import lombok.*;


import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "PRODUCT_ITEM")
public class ProductItem extends BaseDate{

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @Column(name = "WEIGHT_KG", nullable = false)
    private Integer weightKg;

    @Column(name = "STATUS")
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus productStatus;

    @Column(name = "VERSION")
    @Version
    private Integer version;

}
