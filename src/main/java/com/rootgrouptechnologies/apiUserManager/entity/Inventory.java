package com.rootgrouptechnologies.apiUserManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventory", schema = "public")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "licence_type_id")
    private Integer licenceTypeId;

    @JsonFormat(pattern = "YYYY-MM-dd")
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "delete_after_drop")
    private Boolean deleteAfterDrop;

    @Column(name = "auto_restock")
    private Boolean autoRestock;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "initial_qty")
    private Integer initialQty;
}
