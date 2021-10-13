package com.rootgrouptechnologies.apiUserManager.entity;

import javax.persistence.*;

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

    @Column(name = "cart_number_ending")
    private Integer cartNumberEnding;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() { return id; }

    public Boolean getActive() {
        return active;
    }

    public String getCardDate() {
        return cardDate;
    }

    public Integer getCartNumberEnding() {
        return cartNumberEnding;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Integer getUserId() {
        return userId;
    }
}
