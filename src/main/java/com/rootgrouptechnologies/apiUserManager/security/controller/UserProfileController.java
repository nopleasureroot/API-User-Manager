package com.rootgrouptechnologies.apiUserManager.security.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
@Slf4j
@NoArgsConstructor
@RequestMapping("/login")
public class UserProfileController {
    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.discord.client-secret}")
    private String clientSecret;

    @GetMapping("/")
    public ResponseEntity<Void> redirectToLoginPage() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://discord.com/api/oauth2/authorize?client_id=replaceMe&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fuser%2F&response_type=code&scope=identify%20email".replace("replaceMe", clientId))).build();
    }

    @PostMapping("/")
    public ResponseEntity<String> getAccessToken(@RequestBody String code) {
        byte[] encodeId = Base64.getEncoder().encode(clientId.getBytes(StandardCharsets.UTF_8));
        byte[] encodeSecret = Base64.getEncoder().encode(clientSecret.getBytes(StandardCharsets.UTF_8));

        String credentials = new String(encodeId) + ":" + new String(encodeSecret);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String url = "https://discordapp.com/api/oauth2/token?grant_type=authorization_code&code=${code}&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fuser%2F".replace("${code}", code);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        return response;
    }
}
