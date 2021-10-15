package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "licence_type" , schema = "public")
public class LicenceType {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "a_currency")
    private String aCurrency;

    @Column(name = "a_initial_price")
    private Integer aInitialPrice;

    @Column(name = "a_renew_price")
    private Integer aRenewPrice;

    @Column(name = "discord_roles")
    private Long discordRoles;

    @Column(name = "free_to_renew")
    private Boolean freeToRenew;

    @Column(name = "name")
    private String name;

    @Column(name = "p_currency")
    private String pCurrency;

    @Column(name = "p_initial_price")
    private Integer pInitialPrice;

    @Column(name = "p_renew_price")
    private Integer renewPrice;

    @Column(name = "unbindable")
    private Boolean unbindable;

    @Column(name = "lang")
    private String lang;

    @Column(name = "open")
    private Boolean open;

    @Column(name = "major_role_color")
    private Integer majorRoleColor;

    @Column(name = "major_role_name")
    private String majorRoleName;

}
