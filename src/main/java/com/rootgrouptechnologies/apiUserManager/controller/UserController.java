package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.entity.User;
import com.rootgrouptechnologies.apiUserManager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userServiceImpl.deleteUser(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImpl.updateUser(user), HttpStatus.OK);
    }
}
