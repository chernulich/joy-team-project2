package com.coffeeshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ORDER_PRICE")
public class OrderPrice extends BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID", nullable = false)
    private Orders order;

    @Column(name = "SUBTOTAL_PRICE", nullable = false)
    private String SubtotalPrice;

    @Column(name = "SHIPPING_PRICE", nullable = false)
    private Double shippingPrice;

    @Column(name = "DISCOUNT")
    private Double discount;
}
