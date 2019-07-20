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

    private final Double DEFAULT_SHIPPING_PRICE = 0.0;

    @OneToOne
    @JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID", nullable = false)
    private Orders order;

    @Column(name = "SUBTOTAL_PRICE", nullable = false)
    private String SubtotalPrice;

    @Column(name = "SHIPPING_PRICE")
    private Double shippingPrice;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    public Double getShippingPrice() {
        if (shippingPrice == null) {
            return DEFAULT_SHIPPING_PRICE;
        }
        return shippingPrice;
    }

    public void setShippingPrice(Double shippingPrice) {
        if (shippingPrice == null) {
            this.shippingPrice = DEFAULT_SHIPPING_PRICE;
        }
        this.shippingPrice = shippingPrice;
    }
}
