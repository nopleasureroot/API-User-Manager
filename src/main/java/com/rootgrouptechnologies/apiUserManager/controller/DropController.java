package com.rootgrouptechnologies.apiUserManager.controller;

import com.rootgrouptechnologies.apiUserManager.model.request.DropRequest;
import com.rootgrouptechnologies.apiUserManager.service.impl.DropServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drop")
public class DropController {
    private final DropServiceImpl dropService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDrop(@RequestBody DropRequest dropRequest) throws Exception {
        return new ResponseEntity<>(dropService.createDrop(dropRequest) ,HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkInventory(@RequestBody DropRequest dropRequest) {
        return new ResponseEntity<>(dropService.checkInventory(dropRequest), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllDrops() {
        return new ResponseEntity<>(dropService.getAllDrops(), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteDrop(@RequestParam String password) throws Exception {
        return new ResponseEntity<>(dropService.deleteDrop(password), HttpStatus.ACCEPTED);
    }
}
