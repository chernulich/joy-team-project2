package com.coffeeshop.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DynamicInsert
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItems extends  BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    private Orders ordersId;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ITEM_ID", referencedColumnName = "ID", nullable = false)
    private ProductItem productItemId;

    @Builder
    public OrderItems(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Orders ordersId, ProductItem productItemId) {
        super(id, createdOn, updatedOn);
        this.ordersId = ordersId;
        this.productItemId = productItemId;
    }
}
