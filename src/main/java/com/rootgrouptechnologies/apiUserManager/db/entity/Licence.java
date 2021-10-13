package com.rootgrouptechnologies.apiUserManager.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "licence" , schema = "public")
public class Licence {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "renewal_date")
    private Date renewalDate;

    @Column(name = "licence_type_id")
    private Integer licenceTypeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "activated")
    private Boolean activated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public Integer getLicenceTypeId() {
        return licenceTypeId;
    }

    public void setLicenceTypeId(Integer licenceTypeId) {
        this.licenceTypeId = licenceTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
