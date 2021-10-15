package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.mapper.UserMapper;
import com.rootgrouptechnologies.apiUserManager.service.UserDetailService;
import com.rootgrouptechnologies.apiUserManager.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserDetailController {
    private final UserDetailService userDetailService;

    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails() {
        return new ResponseEntity<>(userDetailService.getUsersDetails(), ClassUtils.configureResponseHeaders(), HttpStatus.OK);
    }

}
