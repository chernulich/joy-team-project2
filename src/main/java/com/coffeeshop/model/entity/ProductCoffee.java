package com.coffeeshop.model.entity;

import com.coffeeshop.model.common.CoffeeType;
import com.coffeeshop.model.entity.converter.CoffeeTypeConverter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@DynamicInsert
@Entity
@Table(name = "PRODUCT_COFFEE")
public class ProductCoffee extends BaseDate {

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    private Product product;

    @Column(name = "SOUR", nullable = false)
    private Integer sour;

    @Column(name = "BITTER", nullable = false)
    private Integer bitter;

    @Column(name = "STRONG", nullable = false)
    private Integer strong;

    @Column(name = "GROUND", nullable = false)
    private Boolean ground;

    @Column(name = "DECAF", nullable = false)
    private Boolean decaf;

    @Column(name = "COFFEE_TYPE", nullable = false)
    @Convert(converter = CoffeeTypeConverter.class)
    private CoffeeType coffeeType;

    @Builder
    public ProductCoffee(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Product product, Integer sour,
                         Integer bitter, Integer strong, Boolean ground, Boolean decaf, CoffeeType coffeeType) {
        super(id, createdOn, updatedOn);
        this.product = product;
        this.sour = sour;
        this.bitter = bitter;
        this.strong = strong;
        this.ground = ground;
        this.decaf = decaf;
        this.coffeeType = coffeeType;
    }
}
