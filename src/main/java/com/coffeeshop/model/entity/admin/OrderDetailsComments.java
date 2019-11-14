package com.coffeeshop.model.entity.admin;

import com.coffeeshop.model.entity.BaseDate;
import com.coffeeshop.model.entity.Orders;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@DynamicInsert
@Entity
@Table(name = "ORDER_DETAILS_COMMENTS")
public class OrderDetailsComments extends BaseDate {

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID" , nullable = false)
    private Orders order;

    @OneToOne
    @JoinColumn(name = "MANAGEMENT_USER_ID", referencedColumnName = "ID" , nullable = false)
    private ManagementUsers managementUser;

    @Builder
    public OrderDetailsComments(Long id, LocalDateTime createdOn, LocalDateTime updatedOn,
                                String comment, Orders order, ManagementUsers managementUser) {
        super(id, createdOn, updatedOn);
        this.comment = comment;
        this.order = order;
        this.managementUser = managementUser;
    }
}
