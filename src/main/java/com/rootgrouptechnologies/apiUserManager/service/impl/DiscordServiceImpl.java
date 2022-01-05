package com.rootgrouptechnologies.apiUserManager.service.impl;

import com.rootgrouptechnologies.apiUserManager.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {
    @Value("${discord.bot.token}")
    private String discordBotToken;

    @Value("${discord.guild.id}")
    private String discordGuildId;

    @Override
    public void kickUserFromGuild(String discordId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        requestHeaders.add("User-Agent", "Root");
        requestHeaders.add("Authorization", discordBotToken);

        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);

        String url = "https://discord.com/api/guilds/" + discordGuildId + "/members/" + discordId;

        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }
}
