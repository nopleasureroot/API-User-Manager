package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.service.UserService;
import com.rootgrouptechnologies.apiUserManager.utils.ClassUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.deleteUser(id), ClassUtils.configureResponseHeaders(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.deleteUser(id), ClassUtils.configureResponseHeaders(), HttpStatus.OK);
    }
}
