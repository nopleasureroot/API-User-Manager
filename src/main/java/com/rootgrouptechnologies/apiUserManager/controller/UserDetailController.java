package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.CollectedUser;
import com.rootgrouptechnologies.apiUserManager.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserDetailController {
    private final UserDetailService userDetailService;

    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails() {
        List<CollectedUser> list = userDetailService.getUsersDetails();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS'");
        headers.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

}
