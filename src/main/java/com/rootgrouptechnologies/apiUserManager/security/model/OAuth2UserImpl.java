package com.rootgrouptechnologies.apiUserManager.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class OAuth2UserImpl implements OAuth2User, Serializable {
    private static final long serialVersionUID =  SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String discordId;
    private final String discordUsername;
    private String discordAvatar;
    private final String discordEmail;
    private final Set<GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;

    public OAuth2UserImpl(String discordId, String discordUsername, String discordAvatar, String discordEmail, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
        Assert.notEmpty(authorities, "authorities cannot be empty");
        Assert.notEmpty(attributes, "attributes cannot be empty");
        Assert.hasText(nameAttributeKey, "nameAttributeKey cannot be empty");
        Assert.hasText(discordUsername, "discordUsername cannot be empty");
        Assert.hasText(discordId, "discordId cannot be empty");
        Assert.hasText(discordAvatar, "discordAvatar cannot be empty");
        Assert.hasText(discordEmail, "discordEmail cannot be empty");

        this.discordUsername = discordUsername;
        this.discordId = discordId;
        this.discordAvatar = discordAvatar;
        this.discordEmail = discordEmail;

        if (!attributes.containsKey(nameAttributeKey)) {
            throw new IllegalArgumentException("Missing attribute '" + nameAttributeKey + "' in attributes");
        } else {
            this.authorities = Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)));
            this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
            this.nameAttributeKey = nameAttributeKey;
        }
    }

    @Override
    public String getName() {
        return Objects.requireNonNull(this.getAttribute(this.nameAttributeKey)).toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                Comparator.comparing(GrantedAuthority::getAuthority));
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }

    public String getDiscordId() { return discordId; }

    public String getDiscordAvatar() { return discordAvatar; }

    public String getDiscordEmail() { return discordEmail; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OAuth2UserImpl that = (OAuth2UserImpl) o;
        return Objects.equals(discordId, that.discordId)
                && Objects.equals(discordAvatar, that.discordAvatar)
                && Objects.equals(discordEmail, that.discordEmail)
                && Objects.equals(authorities, that.authorities)
                && Objects.equals(attributes, that.attributes)
                && Objects.equals(nameAttributeKey, that.nameAttributeKey);
    }

    @Override
    public int hashCode() {
        int result = this.getName().hashCode();
        result = 31 * result + this.getAuthorities().hashCode();
        result = 31 * result + this.getAttributes().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OAuth2UserImpl{" +
                "discordId='" + discordId + '\'' +
                ", discordAvatar='" + discordAvatar + '\'' +
                ", discordEmail='" + discordEmail + '\'' +
                ", authorities=" + authorities +
                ", attributes=" + attributes +
                ", nameAttributeKey='" + nameAttributeKey + '\'' +
                '}';
    }
}
