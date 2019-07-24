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
@Table(name = "ORDER_DETAILS")
public class OrderDetails extends BaseDate{

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID" , nullable = false)
    private Orders order;

    @Column(name = "CUSTOMER_ENTITY_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_ENTITY_PHONE_NUMBER")
    private String customerPhoneNumber;

    @Column(name = "ORDER_EMAIL", nullable = false)
    private String orderEmail;

    @Column(name = "CONTACT_FIRST_NAME", nullable = false)
    private String contactFirstName;

    @Column(name = "CONTACT_LAST_NAME", nullable = false)
    private String contactLastName;

    @Column(name = "CONTACT_PHONE_NUMBER", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "IS_SELF_PICKUP", nullable = false)
    private Boolean isSelfPickup;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "APARTMENT")
    private String apartment;

    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "DELIVERY_COMMENT")
    private String deliveryComment;

    @Builder
    public OrderDetails(Long id, LocalDateTime createdOn, LocalDateTime updatedOn, Orders order, String customerName,
                        String customerPhoneNumber, String orderEmail, String contactFirstName, String contactLastName,
                        String contactPhoneNumber, Boolean isSelfPickup, String city, String street, String houseNumber,
                        String apartment, String floor, String deliveryComment) {
        super(id, createdOn, updatedOn);
        this.order = order;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderEmail = orderEmail;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.isSelfPickup = isSelfPickup;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartment = apartment;
        this.floor = floor;
        this.deliveryComment = deliveryComment;
    }
}

