package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "billing", schema = "public")
public class Billing {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "card_date")
    private String cardDate;

    @Column(name = "card_number_ending")
    private Integer cartNumberEnding;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "user_id")
    private Integer userId;

}
