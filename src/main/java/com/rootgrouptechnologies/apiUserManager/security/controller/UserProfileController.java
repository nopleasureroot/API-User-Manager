package com.rootgrouptechnologies.apiUserManager.security.controller;

import com.rootgrouptechnologies.apiUserManager.security.service.OAuth2AccessTokenResponseClientImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class UserProfileController {
    @Value("${spring.security.oauth2.client.registration.discord.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.discord.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.discord.redirect-uri}")
    private String redirectUri;

    private OAuth2AccessTokenResponseClientImpl oAuth2AccessTokenResponseClient;

    @GetMapping("/")
    public ResponseEntity<Void> redirectToLoginPage() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://discord.com/api/oauth2/authorize?client_id=replaceMe&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fuser%2F&response_type=code&scope=identify%20email".replace("replaceMe", clientId))).build();
    }

    @PostMapping("/")
    public ResponseEntity<OAuth2AccessTokenResponse> getAccessToken(@RequestBody String authCode){
        OAuth2AuthorizationCodeGrantRequest oAuth2AuthorizationCodeGrantRequest = new OAuth2AuthorizationCodeGrantRequest(ClientRegistration.withRegistrationId(authCode).clientId(clientId).clientSecret(clientSecret).authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).redirectUri(redirectUri).build(), new OAuth2AuthorizationExchange(OAuth2AuthorizationRequest.authorizationCode().build(), OAuth2AuthorizationResponse.error("500").build()));

        return new ResponseEntity<>(oAuth2AccessTokenResponseClient.getTokenResponse(oAuth2AuthorizationCodeGrantRequest), HttpStatus.OK);
    }
}
