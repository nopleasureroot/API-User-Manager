package com.rootgrouptechnologies.apiUserManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drop")
public class DropController {


    @PostMapping("/create")
    public ResponseEntity<Object> createDrop() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
