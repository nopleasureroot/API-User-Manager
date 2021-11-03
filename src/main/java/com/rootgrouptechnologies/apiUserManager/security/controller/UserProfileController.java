package com.rootgrouptechnologies.apiUserManager.security.controller;

import com.rootgrouptechnologies.apiUserManager.security.model.OAuth2UserImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/details")
public class UserProfileController {

    @GetMapping("/")
    public ResponseEntity<Object> getUserProfile() {
        OAuth2UserImpl oAuth2User = (OAuth2UserImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        oAuth2User.setDiscordAvatar("https://cdn.discordapp.com/avatars/" + oAuth2User.getDiscordId() + "/" + oAuth2User.getDiscordAvatar() + ".jpeg");

        return new ResponseEntity<>(oAuth2User, HttpStatus.OK);
    }
}
