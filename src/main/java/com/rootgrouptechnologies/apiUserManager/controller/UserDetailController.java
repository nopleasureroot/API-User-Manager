package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserDetailController {
    private final UserDetailServiceImpl userDetailServiceImpl;

    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails(Authentication authentication) {
        int u = 0;

        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(userDetailServiceImpl.getUsersDetails(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
