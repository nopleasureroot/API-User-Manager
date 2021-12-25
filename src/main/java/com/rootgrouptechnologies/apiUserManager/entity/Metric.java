package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "members_analytic", schema = "public")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "date")
    private String date;
}
