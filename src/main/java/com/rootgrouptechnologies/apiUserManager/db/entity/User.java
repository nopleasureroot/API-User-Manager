package com.rootgrouptechnologies.apiUserManager.db.entity;

import javax.persistence.*;
import java.util.Date;

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
    private String discordId;

    @Column(name = "discord_username")
    private String discordUsername;

    @Column(name = "creation_date")
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscordAvatar() {
        return discordAvatar;
    }

    public void setDiscordAvatar(String discordAvatar) {
        this.discordAvatar = discordAvatar;
    }

    public String getDiscordEmail() {
        return discordEmail;
    }

    public void setDiscordEmail(String discordEmail) {
        this.discordEmail = discordEmail;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getDiscordUsername() {
        return discordUsername;
    }

    public void setDiscordUsername(String discordUsername) {
        this.discordUsername = discordUsername;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
