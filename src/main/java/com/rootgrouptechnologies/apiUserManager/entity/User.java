package com.rootgrouptechnologies.apiUserManager.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "discord_avatar")
    private String discordAvatar;

    @Column(name = "discord_email")
    private String discordEmail;

    @Column(name = "discord_id")
    private Long discordId;

    @Column(name = "discord_username")
    private String discordUsername;

    @Column(name = "creation_date")
    private String creationDate;

    public String getCreationDate() {
        return creationDate.split(" ")[0];
    }
}
