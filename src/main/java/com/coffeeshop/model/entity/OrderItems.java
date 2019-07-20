package com.coffeeshop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItems extends  BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    private Orders ordersId;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ITEM_ID", referencedColumnName = "ID", nullable = false)
    private ProductItem productItemId;
}
