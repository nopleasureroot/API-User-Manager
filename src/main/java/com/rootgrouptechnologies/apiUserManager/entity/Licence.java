package com.rootgrouptechnologies.apiUserManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "licence" , schema = "public")
public class Licence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonFormat(pattern = "YYYY-MM-dd")
    @JsonSerialize(using = DateSerializer.class)
    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "renewal_date")
    private String renewalDate;

    @Column(name = "licence_type_id")
    private Integer licenceTypeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "activated")
    private Boolean activated;
}
