package com.rootgrouptechnologies.apiUserManager.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class UserAuthenticatedController {
    @GetMapping()
    public ResponseEntity<Object> getDiscordProfile(Authentication authentication) {
       return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }
}
