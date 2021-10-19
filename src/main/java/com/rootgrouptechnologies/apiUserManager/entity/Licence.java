package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "licence" , schema = "public")
public class Licence {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

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
