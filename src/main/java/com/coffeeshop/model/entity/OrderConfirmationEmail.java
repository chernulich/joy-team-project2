package com.coffeeshop.model.entity;

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
@Table(name = "ORDER_CONFIRMATION_EMAIL")
public class OrderConfirmationEmail extends BaseDate {

    @OneToOne
    @JoinColumn(name = "ORDER_ID" , referencedColumnName = "ID" , nullable = false)
    private Orders order;

    @Column(name = "ORDER_EMAIL" , nullable = false , length = 50)
    private String orderEmail;

    @Column(name = "EMAIL_PARTS" , nullable = false)
    private String emailParts;

    @Column(name = "IS_SEND_FAILED" , nullable = false)
    private Boolean isSendFailed;

    @Column(name = "IS_LOCKED" , nullable = false)
    private Boolean isLocked;

    @Column(name = "LOCK_ACQUIRED_ON" , nullable = false)
    private LocalDateTime lockAcquiredOn;

    @Column(name = "VERSION")
    @Version
    private Integer version;

    @Builder
    public OrderConfirmationEmail(Long id,
                                  LocalDateTime createdOn,
                                  LocalDateTime updatedOn,
                                  Orders order,
                                  String orderEmail,
                                  String emailParts,
                                  Boolean isSendFailed,
                                  Boolean isLocked,
                                  LocalDateTime lockAcquiredOn,
                                  Integer version) {
        super(id, createdOn, updatedOn);
        this.order = order;
        this.orderEmail = orderEmail;
        this.emailParts = emailParts;
        this.isSendFailed = isSendFailed;
        this.isLocked = isLocked;
        this.lockAcquiredOn = lockAcquiredOn;
        this.version = version;
    }
}
