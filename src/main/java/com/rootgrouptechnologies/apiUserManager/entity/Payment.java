package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payments_analytics", schema = "public")
public class Payment {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "discord_id")
    private Long discordId;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "discord_username")
    private String discordUsername;

    @Column(name = "discord_email")
    private String discordEmail;

    @Column(name = "payment_state")
    private String paymentState;
}
