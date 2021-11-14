package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserDetailController {
    private final UserDetailServiceImpl userDetailServiceImpl;

    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails(Authentication authentication) {
        return new ResponseEntity<>(userDetailServiceImpl.getUsersDetails(), HttpStatus.OK);
    }
}
