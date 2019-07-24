package com.coffeeshop.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@DynamicInsert
@Entity
@Table(name = "ORDER_PRICE")
public class OrderPrice extends BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID", nullable = false)
    private Orders order;

    @Column(name = "SUBTOTAL_PRICE", nullable = false)
    private String subtotalPrice;

    @Column(name = "SHIPPING_PRICE", nullable = false)
    private Double shippingPrice;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Builder
    public OrderPrice(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Orders order, String subtotalPrice,
                      Double shippingPrice, Double discount) {
        super(id, createdOn, updatedOn);
        this.order = order;
        this.subtotalPrice = subtotalPrice;
        this.shippingPrice = shippingPrice;
        this.discount = discount;
    }
}
